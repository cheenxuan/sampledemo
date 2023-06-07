package com.mobile.base.sampledemo.domain.mappers

import com.mobile.base.sampledemo.domain.models.Character
import com.mobile.base.sampledemo.domain.models.Episode
import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import com.mobile.base.sampledemo.network.response.GetEpisodeByIdResponse

/**
 * @author Xuan
 * @date 2023-06-07
 * <p>
 * @description
 * <p>
 */
object CharacterMapper {

    fun buildFrom(
        response: GetCharacterByIdResponse,
        episodes: List<GetEpisodeByIdResponse>
    ): Character {
        return Character(
            episodeList = episodes.map {
                EpisodeMapper.buildFrom(it)
            },
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}