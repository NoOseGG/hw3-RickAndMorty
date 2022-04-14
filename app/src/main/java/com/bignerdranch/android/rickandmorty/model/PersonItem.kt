package com.bignerdranch.android.rickandmorty.model

import java.io.Serializable

sealed class PersonItem {

    data class Person(
        val id: Int,
        val name: String,
        val image: String,
        val status: String,
        val species: String,
        val type: String,
        val gender: String,
    ) : PersonItem(), Serializable

    object Loading : PersonItem()
}