package com.github.lzaytseva.moviesimdbapi.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieCastRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieDetailsRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.MoviesSearchRequest
import com.github.lzaytseva.moviesimdbapi.data.dto.Response

class RetrofitNetworkClient
    (
    private val context: Context,
    private val imdbService: IMDbApiService
) : NetworkClient {

    override fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }

        if ((dto !is MoviesSearchRequest) && (dto !is MovieDetailsRequest) && (dto !is MovieCastRequest)) {
            return Response().apply { resultCode = 400 }
        }

        val response = when (dto) {
            is MoviesSearchRequest -> imdbService.searchMovies(dto.expression).execute()
            is MovieDetailsRequest -> imdbService.getMovieDetails(dto.movieId).execute()
            else -> imdbService.getMovieCast((dto as MovieCastRequest).movieId).execute()
        }
        val body = response.body()
        return body?.apply { resultCode = response.code() } ?: Response().apply {
            resultCode = response.code()
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    companion object {
        const val IMDB_BASE_URL = "https://imdb-api.com"
    }
}