package org.kverenev.rickandmortycharacters.data

import com.squareup.moshi.Json

data class CharacterDetails(
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "status")
    val status: String
)