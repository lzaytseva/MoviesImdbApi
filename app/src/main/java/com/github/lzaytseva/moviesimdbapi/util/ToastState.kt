package com.github.lzaytseva.moviesimdbapi.util

sealed interface ToastState {
    object None : ToastState
    data class Show(val additionalMessage: String) : ToastState
}