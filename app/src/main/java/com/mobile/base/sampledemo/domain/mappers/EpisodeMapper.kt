package com.mobile.base.sampledemo.domain.mappers

import com.mobile.base.sampledemo.domain.models.Episode
import com.mobile.base.sampledemo.network.response.GetEpisodeByIdResponse

/**
 * @author Xuan
 * @date 2023-06-07
 * <p>
 * @description
 * <p>
 */
object EpisodeMapper {
    fun buildFrom(networkEpisode: GetEpisodeByIdResponse): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            episode = networkEpisode.episode
        )
    }
}