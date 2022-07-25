package org.kverenev.rickandmortycharacters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kverenev.rickandmortycharacters.data.Repository
import org.kverenev.rickandmortycharacters.data.Character
import java.lang.Exception

class CharacterListViewModel(private val repository: Repository) :
    ViewModel() {

    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>>>()
    val charactersLiveData: LiveData<ScreenState<List<Character>>> = _charactersLiveData

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        _charactersLiveData.postValue(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = repository.getCharacters("1")
                _charactersLiveData.postValue(ScreenState.Success(client.result))
            } catch (e: Exception) {
                _charactersLiveData.postValue(ScreenState.Error(e.message.toString()))
            }
        }
    }
}