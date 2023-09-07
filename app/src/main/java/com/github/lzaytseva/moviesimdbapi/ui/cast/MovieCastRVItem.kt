package com.github.lzaytseva.moviesimdbapi.ui.cast

import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCastPerson
import com.github.lzaytseva.moviesimdbapi.ui.core.RVItem

sealed interface MovieCastRVItem: RVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MovieCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MovieCastRVItem

}