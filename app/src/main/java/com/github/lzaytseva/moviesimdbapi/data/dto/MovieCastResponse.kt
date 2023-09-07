package com.github.lzaytseva.moviesimdbapi.data.dto

data class MovieCastResponse(
    val actors: List<Actor>,
    val directors: Directors,
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val others: List<Other>,
    val title: String,
    val type: String,
    val writers: Writers,
    val year: String
): Response()

data class Actor(
    val asCharacter: String,
    val id: String,
    val image: String,
    val name: String
)

data class Directors(
    val items: List<Item>,
    val job: String
)

data class Other(
    val items: List<Item>,
    val job: String
)

data class Writers(
    val items: List<Item>,
    val job: String
)

data class Item(
    val description: String,
    val id: String,
    val name: String
)