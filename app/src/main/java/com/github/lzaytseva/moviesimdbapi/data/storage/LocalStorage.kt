package com.github.lzaytseva.moviesimdbapi.data.storage

import android.content.SharedPreferences

class LocalStorage(private val sharedPreferences: SharedPreferences) : Storage {

    override fun addToFavorites(movieId: String) {
        changeFavorites(movieId = movieId, remove = false)
    }

    override fun removeFromFavorites(movieId: String) {
        changeFavorites(movieId = movieId, remove = true)
    }

    override fun getSavedFavorites(): Set<String> {
        return sharedPreferences.getStringSet(FAVORITES_KEY, emptySet()) ?: emptySet()
    }

    private fun changeFavorites(movieId: String, remove: Boolean) {
        val mutableSet = getSavedFavorites().toMutableSet()
        val modified = if (remove) mutableSet.remove(movieId) else mutableSet.add(movieId)
        if (modified) sharedPreferences.edit().putStringSet(FAVORITES_KEY, mutableSet).apply()
    }

    private companion object {
        const val FAVORITES_KEY = "FAVORITES_KEY"
    }
}