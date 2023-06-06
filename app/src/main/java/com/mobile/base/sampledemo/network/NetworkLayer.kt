package com.mobile.base.sampledemo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * @author Xuan
 * @date 2023-06-05
 * <p>
 * @description
 * <p>
 */
object NetworkLayer {
    //创建moshi
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    //创建retrofit
    val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val rickAndMortyService: RickAndMortyService by lazy {
        retrofit.create(RickAndMortyService::class.java)
    }

    val apiClient = ApiClient(rickAndMortyService)
}