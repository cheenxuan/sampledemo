package com.mobile.base.sampledemo

import com.mobile.base.sampledemo.domain.mappers.CharacterMapper
import com.mobile.base.sampledemo.domain.models.Character
import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import com.mobile.base.sampledemo.network.NetworkLayer
import com.mobile.base.sampledemo.network.response.GetEpisodeByIdResponse

/**
 * @author Xuan
 * @date 2023-06-05
 * <p>
 * @description
 * <p>
 */
class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisode = getEpisodeFromCharacterResponse(request.body)

        return CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisode
        )
    }

    private suspend fun getEpisodeFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)


        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }


        return request.body
    }
}
