package com.github.lzaytseva.moviesimdbapi.data.dto

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<MovieDto>) : Response()