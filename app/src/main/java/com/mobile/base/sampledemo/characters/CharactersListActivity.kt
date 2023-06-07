package com.mobile.base.sampledemo.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.mobile.base.sampledemo.Constants.INTENT_EXTRA_CHARACTER_ID
import com.mobile.base.sampledemo.MainActivity
import com.mobile.base.sampledemo.R

/**
 * @author Xuan
 * @date 2023-06-07
 * <p>
 * @description
 * <p>
 */
class CharactersListActivity : AppCompatActivity() {

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_list)

        viewModel.charactersPagedListLiveData.observe(this) { pageList ->
            epoxyController.submitList(pageList)
        }

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setController(epoxyController)

    }

    private fun onCharacterSelected(characterId: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(INTENT_EXTRA_CHARACTER_ID,characterId)
        startActivity(intent)
    }
}