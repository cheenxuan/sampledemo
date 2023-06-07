package com.mobile.base.sampledemo.network

import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import com.mobile.base.sampledemo.network.response.GetCharactersPageResponse
import com.mobile.base.sampledemo.network.response.GetEpisodeByIdResponse
import retrofit2.Response


/**
 * @author Xuan
 * @date 2023-06-05
 * <p>
 * @description
 * <p>
 */
class ApiClient(
    private val rickAndMortyService: RickAndMortyService
) {

    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }

    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse> {
        return safeApiCall { rickAndMortyService.getEpisodeById(episodeId) }
    }

    suspend fun getEpisodeRange(episodeRange: String): SimpleResponse<List<GetEpisodeByIdResponse>> {
        return safeApiCall { rickAndMortyService.getEpisodeRange(episodeRange) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}