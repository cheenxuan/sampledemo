package com.mobile.base.sampledemo.characters

import com.mobile.base.sampledemo.network.NetworkLayer
import com.mobile.base.sampledemo.network.response.GetCharactersPageResponse

class CharactersRepository {

    suspend fun getCharacterPage(pageIndex: Int): GetCharactersPageResponse? {
        val request=NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body!!
    }
}
