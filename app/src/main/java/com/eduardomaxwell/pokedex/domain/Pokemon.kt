package com.eduardomaxwell.pokedex.domain

data class Pokemon(
    val imageURl: String,
    val number: Int,
    val name: String,
    val types: List<PokemonType>
) {

    fun formmatedNumber() = number.toString().padStart(3, '0')
}

