package com.github.lzaytseva.moviesimdbapi.data.storage

interface Storage {
    fun addToFavorites(movieId: String)

    fun removeFromFavorites(movieId: String)

    fun getSavedFavorites(): Set<String>
}