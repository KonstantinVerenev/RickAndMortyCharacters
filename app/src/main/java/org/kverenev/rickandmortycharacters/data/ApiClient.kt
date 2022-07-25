package org.kverenev.rickandmortycharacters.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.kverenev.rickandmortycharacters.data.CharacterDetails
import org.kverenev.rickandmortycharacters.data.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiClient {
//    https://rickandmortyapi.com/api/character/?page=1
//    https://rickandmortyapi.com/api/character/1

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService {
    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: String): CharacterResponse

    @GET("character/{id}")
    suspend fun fetchCharacterDetails(@Path("id") id: String): CharacterDetails
}