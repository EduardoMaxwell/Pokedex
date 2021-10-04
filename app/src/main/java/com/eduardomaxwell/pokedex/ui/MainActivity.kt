package com.eduardomaxwell.pokedex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.eduardomaxwell.pokedex.databinding.ActivityMainBinding
import com.eduardomaxwell.pokedex.domain.Pokemon
import com.eduardomaxwell.pokedex.ui.viewmodel.PokemonViewModel
import com.eduardomaxwell.pokedex.ui.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.pokemons.observe(this, Observer {
            it?.let { list ->
                loadRecyclerView(list)

            }
        })
    }


    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        binding.rvPorkemons.apply {
            setHasFixedSize(true)
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = PokemonAdapter(pokemons)
        }


    }

}