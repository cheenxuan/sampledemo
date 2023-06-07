package com.mobile.base.sampledemo.network.response

/**
 * @author Xuan
 * @date 2023-06-08
 * <p>
 * @description
 * <p>
 */
data class GetEpisodeByIdResponse(
    val air_date: String = "",
    val character: List<String> = listOf(),
    val create: String = "",
    val episode: String = "",
    val id: Int = 0,
    val name: String = "",
    val url: String = ""
)
