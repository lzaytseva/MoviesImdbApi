package com.github.lzaytseva.moviesimdbapi.ui.about

import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}