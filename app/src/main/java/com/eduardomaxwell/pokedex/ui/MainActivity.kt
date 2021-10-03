package com.eduardomaxwell.pokedex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eduardomaxwell.pokedex.databinding.ActivityMainBinding
import com.eduardomaxwell.pokedex.domain.Pokemon
import com.eduardomaxwell.pokedex.domain.PokemonType

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPorkemons.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = PokemonAdapter(getPokemons())
        }
    }

    private fun getPokemons(): List<Pokemon> {
        return listOf(
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/004.png",
                1,
                "Charmander",
                listOf(
                    PokemonType("Fire")
                )

            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/005.png",
                2,
                "Charmeleon",
                listOf(
                    PokemonType("Fire")
                )

            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/006.png",
                3,
                "Charizard",
                listOf(
                    PokemonType("Fire")
                )

            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/037.png",
                4,
                "Vulpix",
                listOf(
                    PokemonType("Fire")
                )

            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/126.png",
                5,
                "Magmar",
                listOf(
                    PokemonType("Fire")
                )

            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/155.png",
                6,
                "Cyndaquil",
                listOf(
                    PokemonType("Fire")
                )

            ),
            Pokemon(
                "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/390.png",
                6,
                "Chimchar",
                listOf(
                    PokemonType("Fire")
                )

            )
        )
    }
}