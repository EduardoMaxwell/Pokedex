package com.eduardomaxwell.pokedex.api

import com.eduardomaxwell.pokedex.api.model.PokemonAPIResult
import com.eduardomaxwell.pokedex.api.model.PokemonsAPIResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val service: PokemonService = initRetrofit().create(PokemonService::class.java)

    fun listPokemons(limit: Int = 21): PokemonsAPIResult? {
        val call = service.getPokemons(limit)

        return call.execute().body()
    }

    fun getPokemon(number: Int): PokemonAPIResult? {
        val call = service.getPokemon(number)

        return call.execute().body()
    }
}