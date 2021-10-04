package com.eduardomaxwell.pokedex.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.eduardomaxwell.pokedex.R
import com.eduardomaxwell.pokedex.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_Pokedex)
        setContentView(binding.root)

        binding.tvPokemonNameDetail.text = intent.getStringExtra(EXTRA_NAME)

        Glide.with(this)
            .load(intent.getStringExtra(EXTRA_URL))
            .centerCrop()
            .into(binding.ivPokemonImageDetail)

    }

    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"
        private const val EXTRA_URL = "EXTRA_URL"

        fun getStartIntent(context: Context, pokemonName: String, imageUri: String): Intent {
            return Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(EXTRA_NAME, pokemonName.capitalize())
                putExtra(EXTRA_URL, imageUri)
            }
        }
    }
}