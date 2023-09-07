package com.github.lzaytseva.moviesimdbapi.domain.api

import com.github.lzaytseva.moviesimdbapi.domain.model.Movie
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCast
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails
import com.github.lzaytseva.moviesimdbapi.util.Resource


interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
    fun getMovieDetails(movieId: String): Resource<MovieDetails>
    fun getMovieCast(movieId: String): Resource<MovieCast>
}