package com.decadev.pokemonapp.decoration

import android.graphics.Color

object CommonUse {

    fun getColorByType(type: String): Int {
        return when (type) {
            "normal" -> Color.parseColor("#A4A27A")
            "dragon" -> Color.parseColor("#743BFB")
            "psychic" -> Color.parseColor("#F15B85")
            "electric" -> Color.parseColor("#E9CA3C")
            "ground" -> Color.parseColor("#D9BF6C")
            "grass" -> Color.parseColor("#81C85B")
            "poison" -> Color.parseColor("#A441A3")
            "steel" -> Color.parseColor("#BAB7D2")
            "fairy" -> Color.parseColor("#DDA2DF")
            "fire" -> Color.parseColor("#F48130")
            "fight" -> Color.parseColor("#BE3027")
            "bug" -> Color.parseColor("#A8B822")
            "ghost" -> Color.parseColor("#705693")
            "dark" -> Color.parseColor("#745945")
            "ice" -> Color.parseColor("#9BD8D8")
            "water" -> Color.parseColor("#658FF1")
            else -> Color.parseColor("#658FA0")
        }
    }
}