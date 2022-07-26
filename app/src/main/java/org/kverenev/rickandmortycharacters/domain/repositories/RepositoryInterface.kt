package org.kverenev.rickandmortycharacters.domain.repositories

import org.kverenev.rickandmortycharacters.domain.models.CharacterItem

interface RepositoryInterface {

    suspend fun getCharacters(page: String): List<CharacterItem>

    suspend fun getCharacterDetails(id: String): CharacterItem
}