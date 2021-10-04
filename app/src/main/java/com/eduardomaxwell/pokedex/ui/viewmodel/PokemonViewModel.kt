package com.eduardomaxwell.pokedex.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduardomaxwell.pokedex.api.PokemonRepository
import com.eduardomaxwell.pokedex.domain.Pokemon

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread {
            loadPokemons()
        }.start()
    }

    private fun loadPokemons() {
        val pokemonsApiResults = PokemonRepository.listPokemons()
        pokemonsApiResults?.results?.let { pokemonsResults ->

            pokemons.postValue(pokemonsResults.map { pokemonResult ->
                val number =
                    pokemonResult.url
                        .replace("https://pokeapi.co/api/v2/pokemon/", "")
                        .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)
                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }

            })
        }
    }
}