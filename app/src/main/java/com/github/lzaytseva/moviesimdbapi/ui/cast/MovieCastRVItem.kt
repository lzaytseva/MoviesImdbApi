package com.github.lzaytseva.moviesimdbapi.ui.cast

import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCastPerson

sealed interface MovieCastRVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MovieCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MovieCastRVItem

}