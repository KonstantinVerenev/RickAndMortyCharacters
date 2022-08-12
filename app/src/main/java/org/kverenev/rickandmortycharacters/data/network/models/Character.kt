package org.kverenev.rickandmortycharacters.data.network.models

import com.squareup.moshi.Json

data class Character(
    @Json(name = "id")
    val id: String,
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

/**
 * 2! Сюда мап
 */