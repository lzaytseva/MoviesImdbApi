package com.github.lzaytseva.moviesimdbapi.data.repository

import com.github.lzaytseva.moviesimdbapi.data.dto.MovieCastRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieCastResponse
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieDetailsRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieDetailsResponse
import com.github.lzaytseva.moviesimdbapi.data.dto.MoviesSearchRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.MoviesSearchResponse
import com.github.lzaytseva.moviesimdbapi.data.mapper.MovieCastMapper
import com.github.lzaytseva.moviesimdbapi.data.network.NetworkClient
import com.github.lzaytseva.moviesimdbapi.data.storage.Storage
import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesRepository
import com.github.lzaytseva.moviesimdbapi.domain.model.Movie
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCast
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails
import com.github.lzaytseva.moviesimdbapi.util.Resource


class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val localStorage: Storage,
    private val castMapper: MovieCastMapper
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

    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }

            200 -> {
                Resource.Success(
                    data = castMapper.mapDtoToDomain(response as MovieCastResponse)
                )
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