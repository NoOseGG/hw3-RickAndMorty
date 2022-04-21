package com.bignerdranch.android.rickandmorty.retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RickAndMortyService {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    private var retrofit: RickAndMortyAPI? = null

    private fun provideRickAndMortyApi() : RickAndMortyAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    fun getInstance(): RickAndMortyAPI {
        if(retrofit == null) {
            retrofit = provideRickAndMortyApi()
        }
        return retrofit as RickAndMortyAPI
    }
}
