package com.bignerdranch.android.rickandmorty.model

data class ListPerson(
    val info: Info,
    val results: List<PersonItem.Person>,
)

data class Info(
    val count: Int,
    val pages: Int,
)