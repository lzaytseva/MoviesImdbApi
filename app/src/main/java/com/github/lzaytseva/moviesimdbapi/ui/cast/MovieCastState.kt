package com.github.lzaytseva.moviesimdbapi.ui.cast

import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCast
import com.github.lzaytseva.moviesimdbapi.ui.core.RVItem

sealed interface MovieCastState {

    object Loading : MovieCastState

    data class Content(
        val fullTitle: String,
        val items: List<RVItem>,
    ) : MovieCastState

    data class Error(
        val message: String
    ) : MovieCastState
}
