package org.kverenev.rickandmortycharacters.presentation.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kverenev.rickandmortycharacters.data.Repository
import org.kverenev.rickandmortycharacters.domain.models.CharacterItem
import org.kverenev.rickandmortycharacters.domain.usecases.GetCharactersUseCase
import org.kverenev.rickandmortycharacters.presentation.ScreenState
import java.lang.Exception

class CharacterListViewModel(repository: Repository) :
    ViewModel() {

    private val getCharactersUseCase = GetCharactersUseCase(repository)

    private var _charactersLiveData = MutableLiveData<ScreenState<List<CharacterItem>>>()
    val charactersLiveData: LiveData<ScreenState<List<CharacterItem>>> = _charactersLiveData

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        _charactersLiveData.postValue(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val charactersList = getCharactersUseCase.execute()
                _charactersLiveData.postValue(ScreenState.Success(charactersList))
            } catch (e: Exception) {
                _charactersLiveData.postValue(ScreenState.Error(e.message.toString()))
            }
        }
    }
}