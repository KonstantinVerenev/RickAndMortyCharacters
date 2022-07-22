package org.kverenev.rickandmortycharacters.network

import com.squareup.moshi.Json

data class CharacterResponse(
    @Json(name = "results")
    val result: List<Character>
)