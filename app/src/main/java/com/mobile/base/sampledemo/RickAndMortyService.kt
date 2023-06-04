package com.mobile.base.sampledemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Xuan
 * @date 2023-06-04
 * <p>
 * @description
 * <p>
 */
interface RickAndMortyService {

    @GET("character/{character-id}")
    fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Call<GetCharacterByIdResponse>

}