package com.eduardomaxwell.pokedex.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eduardomaxwell.pokedex.api.PokemonRepository
import com.eduardomaxwell.pokedex.databinding.ActivityMainBinding
import com.eduardomaxwell.pokedex.domain.Pokemon

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Thread {
            loadPokemons()
        }.start()
    }

    private fun loadPokemons() {
        val pokemonsApiResults = PokemonRepository.listPokemons()
        pokemonsApiResults?.results?.let { pokemonsResults ->

            val pokemons: List<Pokemon?> = pokemonsResults.map { pokemonResult ->
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

            }

            binding.rvPorkemons.post {
                binding.rvPorkemons.apply {
                    setHasFixedSize(true)
                    layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    adapter = PokemonAdapter(pokemons)
                }

            }

            Log.d("POKEMON_API", pokemonsResults.toString())
        }
    }

//    private fun getPokemons(): List<Pokemon> {
//        return listOf(
//            Pokemon(
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
//                1,
//                "Charmander",
//                listOf(
//                    PokemonType("Fire")
//                )
//
//            ),
//            Pokemon(
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/005.png",
//                2,
//                "Charmeleon",
//                listOf(
//                    PokemonType("Fire")
//                )
//
//            ),
//            Pokemon(
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/006.png",
//                3,
//                "Charizard",
//                listOf(
//                    PokemonType("Fire")
//                )
//
//            ),
//            Pokemon(
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/037.png",
//                4,
//                "Vulpix",
//                listOf(
//                    PokemonType("Fire")
//                )
//
//            ),
//            Pokemon(
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/126.png",
//                5,
//                "Magmar",
//                listOf(
//                    PokemonType("Fire")
//                )
//
//            ),
//            Pokemon(
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/155.png",
//                6,
//                "Cyndaquil",
//                listOf(
//                    PokemonType("Fire")
//                )
//
//            ),
//            Pokemon(
//                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/390.png",
//                6,
//                "Chimchar",
//                listOf(
//                    PokemonType("Fire")
//                )
//
//            )
//        )
//    }
}