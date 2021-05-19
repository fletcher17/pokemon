package com.decadev.pokemonapp.network

import com.decadev.pokemonapp.datamodel.PokemonDetails
import com.decadev.pokemonapp.datamodel.pokemonClass
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon")
     fun get() : Observable<pokemonClass>   //suspend keyword, would be used for

    @GET("pokemon/{id}")
     fun getPokemon(@Path("id") id : Int) : Observable<PokemonDetails>


}