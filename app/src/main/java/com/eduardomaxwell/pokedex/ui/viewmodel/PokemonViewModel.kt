package com.eduardomaxwell.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eduardomaxwell.pokedex.api.PokemonRepository
import com.eduardomaxwell.pokedex.domain.Pokemon

class PokemonViewModel : ViewModel() {
    private val _pokemons = MutableLiveData<List<Pokemon?>>()
    val pokemons: LiveData<List<Pokemon?>> = _pokemons

    lateinit var repository: PokemonRepository

    init {
        Thread {
            loadPokemons()
        }.start()
    }

/*    fun getListPokemons(): Flow<PagingData<PokemonResult>> {
        return Pager(
            config = PagingConfig(pageSize = 1118, maxSize = 200),
            pagingSourceFactory = { PokemonPagingSource(repository) }).flow.cachedIn(viewModelScope)
    }*/

    private fun loadPokemons() {
        val pokemonsApiResults = PokemonRepository.listPokemons()

        pokemonsApiResults?.results?.let { pokemonsResults ->

            _pokemons.postValue(pokemonsResults.map { pokemonResult ->
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