package org.kverenev.rickandmortycharacters.data

import kotlinx.coroutines.delay
import org.kverenev.rickandmortycharacters.data.network.ApiService
import org.kverenev.rickandmortycharacters.data.network.models.Character
import org.kverenev.rickandmortycharacters.domain.repositories.RepositoryInterface
import org.kverenev.rickandmortycharacters.domain.models.CharacterItem

class Repository(private val apiService: ApiService) : RepositoryInterface {

    override suspend fun getCharacters(page: String): List<CharacterItem> {
        delay(1000)
        return apiService.fetchCharacters(page).result.map { character -> mapToDomain(character) }
    }

    override suspend fun getCharacterDetails(id: String): CharacterItem {
        delay(1000)
        val character = apiService.fetchCharacterDetails(id)
        return mapToDomain(character)
    }

    private fun mapToDomain(character: Character): CharacterItem {
        return CharacterItem(
            character.id,
            character.name,
            character.image,
            character.species,
            character.gender,
            character.status
        )
    }
}