package com.eduardomaxwell.pokedex.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
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
        setupWindowTransitions()

        binding.apply {
            tvPokemonNameDetail.text = intent.getStringExtra(EXTRA_NAME)
            tvPokemonNameDetail.typeface =
                ResourcesCompat.getFont(applicationContext, R.font.vidaloka_regular)
        }


        Glide.with(this)
            .load(intent.getStringExtra(EXTRA_URL))
            .centerCrop()
            .into(binding.ivPokemonImageDetail)

    }

    private fun setupWindowTransitions() {
        val ttb = AnimationUtils.loadAnimation(this, R.anim.imagescale)
        val btt = AnimationUtils.loadAnimation(this, R.anim.bottomtotop)
        binding.ivPokemonImageDetail.startAnimation(ttb)
        binding.tvPokemonNameDetail.startAnimation(btt)
    }

    companion object {
        private const val EXTRA_NAME = "EXTRA_NAME"
        private const val EXTRA_URL = "EXTRA_URL"

        fun getStartIntent(context: Context, pokemonName: String, imageUri: String) =
            Intent(context, PokemonDetailActivity::class.java).apply {
                putExtra(EXTRA_NAME, pokemonName.capitalize())
                putExtra(EXTRA_URL, imageUri)
            }
    }
}