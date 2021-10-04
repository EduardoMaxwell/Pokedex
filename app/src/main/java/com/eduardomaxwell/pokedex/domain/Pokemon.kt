package com.eduardomaxwell.pokedex.domain

data class Pokemon(
    val number: Int,
    val name: String,
    val types: List<PokemonType>,
) {

    val formmatedNumber = number.toString().padStart(3, '0')
    val imageURl = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$formmatedNumber.png"
}

