package org.kverenev.rickandmortycharacters.data.network.models

import com.squareup.moshi.Json

data class CharactersList(
    @Json(name = "results")
    val result: List<Character>
)