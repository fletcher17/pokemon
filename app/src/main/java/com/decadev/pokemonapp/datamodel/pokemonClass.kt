package com.decadev.pokemonapp.datamodel

data class pokemonClass(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)
