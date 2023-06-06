package com.mobile.base.sampledemo.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.mobile.base.sampledemo.R
import com.mobile.base.sampledemo.databinding.ModelCharactersListItemBinding
import com.mobile.base.sampledemo.epoxy.ViewBindingKotlinModel
import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

/**
 * @author Xuan
 * @date 2023-06-07
 * <p>
 * @description
 * <p>
 */
class CharacterListPagingEpoxyController : PagedListEpoxyController<GetCharacterByIdResponse>() {
    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.image,item.name).id(item.id)
    }

    data class CharacterGridItemEpoxyModel(
        val imageUrl: String,
        val name: String
    ) : ViewBindingKotlinModel<ModelCharactersListItemBinding>(R.layout.model_characters_list_item) {
        override fun ModelCharactersListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
        }

    }
}