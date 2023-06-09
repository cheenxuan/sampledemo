package com.mobile.base.sampledemo

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private val epoxyController= CharacterDetailEpoxyController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.characterByIdLiveData.observe(this) { character ->

            epoxyController.character = character

            if (character == null) {
                Toast.makeText(this, "Unsuccessful network call！", Toast.LENGTH_SHORT).show()
                return@observe
            }
        }

        val id = intent.getIntExtra(Constants.INTENT_EXTRA_CHARACTER_ID, 1)
        viewModel.refreshCharacter(characterId = id)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}