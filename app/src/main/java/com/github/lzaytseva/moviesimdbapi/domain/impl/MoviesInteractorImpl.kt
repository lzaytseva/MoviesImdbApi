package com.github.lzaytseva.moviesimdbapi.domain.impl

import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesInteractor
import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesRepository
import com.github.lzaytseva.moviesimdbapi.domain.model.Movie
import com.github.lzaytseva.moviesimdbapi.util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when (val resource = repository.searchMovies(expression)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }

    override fun getMovieDetails(movieId: String, consumer: MoviesInteractor.MovieDetailsConsumer) {
        executor.execute {
            when (val resource = repository.getMovieDetails(movieId)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }

    override fun getMovieCast(movieId: String, consumer: MoviesInteractor.MovieCastConsumer) {
        executor.execute {
            when (val resource = repository.getMovieCast(movieId)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }
                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        repository.addMovieToFavorites(movie)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        repository.removeMovieFromFavorites(movie)
    }


}