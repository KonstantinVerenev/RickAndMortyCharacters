package org.kverenev.rickandmortycharacters.domain.models

data class CharacterItem(
    val id: String,
    val name: String,
    val image: String,
    val species: String,
    val gender: String,
    val status: String
)