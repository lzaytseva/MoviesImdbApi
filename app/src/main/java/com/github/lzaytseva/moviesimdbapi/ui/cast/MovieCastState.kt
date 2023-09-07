package com.github.lzaytseva.moviesimdbapi.ui.cast

import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCast

sealed interface MovieCastState {
    object Loading : MovieCastState
    data class Content(
        val fullTitle: String,
        val items: List<MovieCastRVItem>,
    ) : MovieCastState

    data class Error(
        val message: String
    ) : MovieCastState

}
