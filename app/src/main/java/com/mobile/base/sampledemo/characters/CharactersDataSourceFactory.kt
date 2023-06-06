package com.mobile.base.sampledemo.characters

import androidx.paging.DataSource
import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

/**
 * @author Xuan
 * @date 2023-06-07
 * <p>
 * @description
 * <p>
 */
class CharactersDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
) : DataSource.Factory<Int, GetCharacterByIdResponse>() {
    override fun create(): DataSource<Int, GetCharacterByIdResponse> {
        return CharactersDataSource(coroutineScope, repository)
    }

}