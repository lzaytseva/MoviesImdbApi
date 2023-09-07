package com.github.lzaytseva.moviesimdbapi.domain.api

import com.github.lzaytseva.moviesimdbapi.domain.model.Movie
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCast
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails


interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MoviesConsumer)

    fun getMovieDetails(movieId: String, consumer: MovieDetailsConsumer)

    fun getMovieCast(movieId: String, consumer: MovieCastConsumer)

    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)

    interface MoviesConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }

    interface MovieDetailsConsumer {
        fun consume(info: MovieDetails?, errorMessage: String?)
    }

    interface MovieCastConsumer {
        fun consume(movieCast: MovieCast?, errorMessage: String?)
    }

}