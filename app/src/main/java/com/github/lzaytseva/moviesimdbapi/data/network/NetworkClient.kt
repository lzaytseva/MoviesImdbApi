package com.github.lzaytseva.moviesimdbapi.data.network

import com.github.lzaytseva.moviesimdbapi.data.dto.Response


interface NetworkClient {
    fun doRequest(dto: Any): Response

}