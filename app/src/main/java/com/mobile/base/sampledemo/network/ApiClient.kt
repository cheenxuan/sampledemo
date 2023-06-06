package com.mobile.base.sampledemo.network

import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
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

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}