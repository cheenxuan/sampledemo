package com.mobile.base.sampledemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<AppCompatTextView>(R.id.nameTextView)
        val headerImageView = findViewById<AppCompatImageView>(R.id.headerIamgeView)
        val aliveTextView = findViewById<AppCompatTextView>(R.id.aliveTextView)
        val originTextView = findViewById<AppCompatTextView>(R.id.originTextView)
        val speciesTextView = findViewById<AppCompatTextView>(R.id.speciesTextView)

        //创建moshi
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        //创建retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        //创建service
        val service: RickAndMortyService = retrofit.create(RickAndMortyService::class.java)

        //创建api请求
        service.getCharacterById(54).enqueue(object : Callback<GetCharacterByIdResponse> {
            override fun onResponse(
                call: Call<GetCharacterByIdResponse>,
                response: Response<GetCharacterByIdResponse>
            ) {
                //请求成功
                Log.d("MainActivity", response.toString())
                if (!response.isSuccessful) {
                    Toast.makeText(
                        this@MainActivity,
                        "Unsuccessful network call!",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                val body = response.body()!!

                nameTextView.text = body.name
                aliveTextView.text = body.status
                speciesTextView.text = body.species
                originTextView.text = body.origin.name

                Picasso.get().load(body.image).into(headerImageView)
            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                //请求失败
                Log.d("MainActivity", t.message ?: "")
            }
        })


    }
}