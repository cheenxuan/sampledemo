package com.mobile.base.sampledemo

import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import com.mobile.base.sampledemo.network.NetworkLayer

/**
 * @author Xuan
 * @date 2023-06-05
 * <p>
 * @description
 * <p>
 */
class SharedRepository {

    suspend fun getCharacterById(characterId: Int): GetCharacterByIdResponse? {
        val request= NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }
}
