package com.github.lzaytseva.moviesimdbapi.data.network

import com.github.lzaytseva.moviesimdbapi.data.dto.MovieCastResponse
import com.github.lzaytseva.moviesimdbapi.data.dto.MovieDetailsResponse
import com.github.lzaytseva.moviesimdbapi.data.dto.MoviesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApiService {
    @GET("en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun searchMovies(@Path("expression") expression: String): Call<MoviesSearchResponse>

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>

    @GET("/en/API/FullCast/k_zcuw1ytf/{movie_id}")
    fun getMovieCast(@Path("movie_id") movieId: String): Call<MovieCastResponse>
}