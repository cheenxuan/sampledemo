package com.mobile.base.sampledemo

import com.airbnb.epoxy.EpoxyController
import com.mobile.base.sampledemo.databinding.ModelCharacterDetailDataPointBinding
import com.mobile.base.sampledemo.databinding.ModelCharacterDetailIamgeBinding
import com.mobile.base.sampledemo.databinding.ModelCharacterDetailsHeaderBinding
import com.mobile.base.sampledemo.epoxy.LoadingEpoxyModel
import com.mobile.base.sampledemo.epoxy.ViewBindingKotlinModel
import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso
import com.mobile.base.sampledemo.domain.models.Character

/**
 * @author Xuan
 * @date 2023-06-06
 * <p>
 * @description
 * <p>
 */
class CharacterDetailEpoxyController : EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var character: Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if (isLoading) {
            //show loading state
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character == null) {
            //todo error state
            return
        }

        //add header model
        HeaderEpoxyModel(
            name=character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("header").addTo(this)
        //add image model
        ImageEpoxyModel(
            imageUrl = character!!.image
        ).id("iamge").addTo(this)
        //add data points model(s)
        DataPointEpoxyModel(
            title = "Origin",
            description = character!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = character!!.species
        ).id("data_point_2").addTo(this)

    }

    data class HeaderEpoxyModel(
        val name: String, val gender: String, val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status
        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailIamgeBinding>(R.layout.model_character_detail_iamge) {
        override fun ModelCharacterDetailIamgeBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailDataPointBinding>(R.layout.model_character_detail_data_point) {
        override fun ModelCharacterDetailDataPointBinding.bind() {
            labelTextView.text = title
            originTextView.text = description
        }

    }
}