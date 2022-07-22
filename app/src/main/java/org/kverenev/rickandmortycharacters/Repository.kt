package org.kverenev.rickandmortycharacters

import kotlinx.coroutines.delay
import org.kverenev.rickandmortycharacters.network.ApiService
import org.kverenev.rickandmortycharacters.network.CharacterResponse
import retrofit2.Call

class Repository(private val apiService: ApiService) {

    suspend fun getCharacters(page: String): CharacterResponse {
        delay(1000)
        return apiService.fetchCharacters(page)
    }
}