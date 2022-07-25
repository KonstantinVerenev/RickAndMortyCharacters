package org.kverenev.rickandmortycharacters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kverenev.rickandmortycharacters.data.CharacterDetails
import org.kverenev.rickandmortycharacters.data.Repository
import java.lang.Exception

class CharacterDetailsViewModels(
    private val repository: Repository,
    private val characterId: String
) : ViewModel() {

    private var _characterDetailsLiveData = MutableLiveData<ScreenState<CharacterDetails>>()
    val characterDetailsLiveData: LiveData<ScreenState<CharacterDetails>> =
        _characterDetailsLiveData

    init {
        fetchCharacterDetails()
    }

    private fun fetchCharacterDetails() {
        _characterDetailsLiveData.postValue(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.getCharacterDetails(characterId)
                _characterDetailsLiveData.postValue(ScreenState.Success(client))
            } catch (e: Exception) {
                _characterDetailsLiveData.postValue(ScreenState.Error(e.message.toString()))
            }
        }
    }
}