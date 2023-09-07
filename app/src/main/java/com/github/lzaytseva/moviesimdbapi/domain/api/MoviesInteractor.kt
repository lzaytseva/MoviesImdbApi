package com.github.lzaytseva.moviesimdbapi.domain.api

import com.github.lzaytseva.moviesimdbapi.domain.model.Movie
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails


interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    fun getMovieDetails(id: String, consumer: MovieDetailsConsumer)

    interface MovieDetailsConsumer {
        fun consume(info: MovieDetails?, errorMessage: String?)
    }

    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
}