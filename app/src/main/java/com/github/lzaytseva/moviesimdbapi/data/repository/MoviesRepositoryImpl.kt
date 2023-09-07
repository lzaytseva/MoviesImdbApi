package com.github.lzaytseva.moviesimdbapi.data.repository

import com.github.lzaytseva.moviesimdbapi.data.dto.MovieDetailsRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieDetailsResponse
import com.github.lzaytseva.moviesimdbapi.data.dto.MoviesSearchRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.MoviesSearchResponse
import com.github.lzaytseva.moviesimdbapi.data.network.NetworkClient
import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesRepository
import com.github.lzaytseva.moviesimdbapi.domain.model.Movie
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails
import com.github.lzaytseva.moviesimdbapi.util.Resource
import com.github.lzaytseva.moviesimdbapi.data.storage.Storage


class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: Storage
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                val stored = localStorage.getSavedFavorites()
                Resource.Success((response as MoviesSearchResponse).results.map {
                    Movie(
                        it.id,
                        it.resultType,
                        it.image,
                        it.title,
                        it.description,
                        stored.contains(it.id)
                    )
                })
            }

            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id, title, imDbRating, year,
                            countries, genres, directors, writers, stars, plot
                        )
                    )
                }
            }

            else -> {
                Resource.Error("Ошибка сервера")

            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}