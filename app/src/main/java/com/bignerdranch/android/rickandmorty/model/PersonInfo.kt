package com.bignerdranch.android.rickandmorty.model

import java.io.Serializable

data class PersonInfo(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val location: Location,
    val episode: List<String>,
) : Serializable

data class Location(
    val name: String,
    val url: String,
)