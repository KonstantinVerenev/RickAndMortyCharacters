package org.kverenev.rickandmortycharacters.presentation.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kverenev.rickandmortycharacters.data.Repository
import org.kverenev.rickandmortycharacters.domain.models.CharacterItem
import org.kverenev.rickandmortycharacters.domain.usecases.GetCharacterDetailsUseCase
import org.kverenev.rickandmortycharacters.presentation.ScreenState
import java.lang.Exception

class CharacterDetailsViewModels(
    repository: Repository,
    private val characterId: String
) : ViewModel() {

    private val getCharacterDetailsUseCase =  GetCharacterDetailsUseCase(repository)

    private var _characterDetailsLiveData = MutableLiveData<ScreenState<CharacterItem>>()
    val characterDetailsLiveData: LiveData<ScreenState<CharacterItem>> =
        _characterDetailsLiveData

    init {
        fetchCharacterDetails()
    }

    private fun fetchCharacterDetails() {
        _characterDetailsLiveData.postValue(ScreenState.Loading(null))

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val character = getCharacterDetailsUseCase.execute(characterId)
                _characterDetailsLiveData.postValue(ScreenState.Success(character))
            } catch (e: Exception) {
                _characterDetailsLiveData.postValue(ScreenState.Error(e.message.toString()))
            }
        }
    }
}