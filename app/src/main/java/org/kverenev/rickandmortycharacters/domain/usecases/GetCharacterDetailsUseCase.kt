package org.kverenev.rickandmortycharacters.domain.usecases

import org.kverenev.rickandmortycharacters.domain.models.CharacterItem
import org.kverenev.rickandmortycharacters.domain.repositories.RepositoryInterface

class GetCharacterDetailsUseCase(private val repository: RepositoryInterface) {

    /**
     *  4! Тут уже депенденси инжекшн
     */

    /**
     *  5! Возможно тутможно добавить интерфейс общего useCase и частные имплементации
     */

    suspend fun execute(id: String): CharacterItem {
        return repository.getCharacterDetails(id)
    }
}