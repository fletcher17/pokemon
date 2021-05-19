package com.decadev.pokemonapp.network

import com.decadev.pokemonapp.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val instance by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//if it was courotine, we won't need this guy
            .build()

    }

    var apiservice : ApiService = instance.create(ApiService::class.java)

}

