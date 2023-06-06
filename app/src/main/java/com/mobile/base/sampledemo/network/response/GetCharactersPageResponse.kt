package com.mobile.base.sampledemo.network.response

/**
 * @author Xuan
 * @date 2023-06-07
 * <p>
 * @description
 * <p>
 */
data class GetCharactersPageResponse(
    val info: Info = Info(),
    val results: List<GetCharacterByIdResponse> = emptyList()
) {
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}
