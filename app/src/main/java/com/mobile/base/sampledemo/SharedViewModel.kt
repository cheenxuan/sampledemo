package com.mobile.base.sampledemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.base.sampledemo.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.launch
import com.mobile.base.sampledemo.domain.models.Character

/**
 * @author Xuan
 * @date 2023-06-05
 * <p>
 * @description
 * <p>
 */
class SharedViewModel:ViewModel() {
    private val repository=SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdLiveData: LiveData<Character?> = _characterByIdLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response=repository.getCharacterById(characterId)

            _characterByIdLiveData.postValue(response)
        }
    }

}
