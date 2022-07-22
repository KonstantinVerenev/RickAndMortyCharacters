package org.kverenev.rickandmortycharacters

import org.kverenev.rickandmortycharacters.network.ApiService
import org.kverenev.rickandmortycharacters.network.CharacterResponse
import retrofit2.Call

class Repository(private val apiService: ApiService) {

    fun getCharacters(page: String): Call<CharacterResponse> {
        return apiService.fetchCharacters(page)
    }
}