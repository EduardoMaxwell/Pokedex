package com.eduardomaxwell.pokedex.utils

import android.content.Context
import com.eduardomaxwell.pokedex.R

object TypeSelector {

    fun typeSelector(context: Context, type: String?): Int? {

        when (type) {

            "rock" -> {
                return R.color.rock
            }
            "bug" -> {
                return R.color.bug
            }
            "ground" -> {
                return R.color.ground
            }
            "fire" -> {
                return R.color.fire
            }
            "water" -> {
                return R.color.water
            }
            "ghost" -> {
                return R.color.ghost
            }
            "dragon" -> {
                return R.color.dragon
            }
            "psychic" -> {
                return R.color.psychic
            }
            "electric" -> {
                return R.color.electric
            }
            "fighting" -> {
                return R.color.fight
            }
            "flying" -> {
                return R.color.flying
            }
            "grass" -> {
                return R.color.grass
            }
            "normal" -> {
                return R.color.normal
            }
            "poison" -> {
                return R.color.poison
            }
            "ice" -> {
                return R.color.ice
            }
            else -> return null
        }

    }
}