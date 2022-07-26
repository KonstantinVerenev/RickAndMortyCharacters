package org.kverenev.rickandmortycharacters.domain.usecases

import org.kverenev.rickandmortycharacters.domain.models.CharacterItem
import org.kverenev.rickandmortycharacters.domain.repositories.RepositoryInterface

class GetCharactersUseCase(private val repository: RepositoryInterface) {

    suspend fun execute() : List<CharacterItem> {
        return repository.getCharacters("1")
    }
}