package com.mobile.base.sampledemo.network

import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import com.mobile.base.sampledemo.network.response.GetCharactersPageResponse
import com.mobile.base.sampledemo.network.response.GetEpisodeByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Xuan
 * @date 2023-06-04
 * <p>
 * @description
 * <p>
 */
interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<GetCharactersPageResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") episodeId: Int
    ): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange: String
    ): Response<List<GetEpisodeByIdResponse>>
}