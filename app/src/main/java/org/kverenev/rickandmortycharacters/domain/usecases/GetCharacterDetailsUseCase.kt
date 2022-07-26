package org.kverenev.rickandmortycharacters.domain.usecases

import org.kverenev.rickandmortycharacters.domain.models.CharacterItem
import org.kverenev.rickandmortycharacters.domain.repositories.RepositoryInterface

class GetCharacterDetailsUseCase(private val repository: RepositoryInterface) {

    suspend fun execute(id: String): CharacterItem {
        return repository.getCharacterDetails(id)
    }
}