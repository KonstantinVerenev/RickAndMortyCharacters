package org.kverenev.rickandmortycharacters.data

import com.squareup.moshi.Json

data class Character(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String
)