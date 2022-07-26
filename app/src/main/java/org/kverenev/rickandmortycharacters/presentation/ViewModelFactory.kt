package org.kverenev.rickandmortycharacters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kverenev.rickandmortycharacters.App
import org.kverenev.rickandmortycharacters.presentation.characterdetails.CharacterDetailsViewModels
import org.kverenev.rickandmortycharacters.presentation.characterlist.CharacterListViewModel

class ViewModelFactory(private val app: App, private val characterId: String? = null) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            CharacterListViewModel::class.java -> {
                CharacterListViewModel(app.repository)
            }
            CharacterDetailsViewModels::class.java -> {
                if (characterId != null) {
                    CharacterDetailsViewModels(app.repository, characterId)
                } else throw Exception("Missing character id")
            }
            // add new view models with params
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }

        return viewModel as T
    }
}

//fun Fragment.factory()= ViewModelFactory(requireContext().applicationContext as App)