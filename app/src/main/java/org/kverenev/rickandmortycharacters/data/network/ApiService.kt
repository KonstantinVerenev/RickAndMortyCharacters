package org.kverenev.rickandmortycharacters.data.network

import org.kverenev.rickandmortycharacters.data.network.models.Character
import org.kverenev.rickandmortycharacters.data.network.models.CharactersList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: String): CharactersList

    @GET("character/{id}")
    suspend fun fetchCharacterDetails(@Path("id") id: String): Character
}

/**
 *  3! Есть ли какойто put или post
 */