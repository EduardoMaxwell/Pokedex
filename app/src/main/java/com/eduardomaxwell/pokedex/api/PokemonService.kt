package com.eduardomaxwell.pokedex.api

import com.eduardomaxwell.pokedex.api.model.PokemonAPIResult
import com.eduardomaxwell.pokedex.api.model.PokemonsAPIResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    fun getPokemons(@Query(value = "limit") limit: Int): Call<PokemonsAPIResult>

    @GET("pokemon/{number}")
    fun getPokemon(@Path("number") number: Int): Call<PokemonAPIResult>
}