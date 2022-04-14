package com.bignerdranch.android.rickandmorty.retrofit

import com.bignerdranch.android.rickandmorty.model.ListPerson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyAPI {

    @GET("character/?")
    fun getAllCharacters(
        @Query("page") page: Int,
    ) : Call<ListPerson>
}