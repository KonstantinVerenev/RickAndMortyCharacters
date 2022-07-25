package org.kverenev.rickandmortycharacters.data

import kotlinx.coroutines.delay
import org.kverenev.rickandmortycharacters.network.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getCharacters(page: String): CharacterResponse {
        delay(1000)
        return apiService.fetchCharacters(page)
    }

    suspend fun getCharacterDetails(id: String): CharacterDetails {
        delay(1000)
        return apiService.fetchCharacterDetails(id)
    }
}