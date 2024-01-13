package com.github.lzaytseva.moviesimdbapi.ui.cast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesInteractor
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieCast

class MovieCastViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<MovieCastState>()
    fun observeState(): LiveData<MovieCastState> = stateLiveData

    init {
        stateLiveData.postValue(MovieCastState.Loading)

        moviesInteractor.getMovieCast(movieId, object : MoviesInteractor.MovieCastConsumer {

            override fun consume(movieCast: MovieCast?, errorMessage: String?) {
                if (movieCast != null) {
                    // добавляем конвертацию в UiState
                    stateLiveData.postValue(castToUiStateContent(movieCast))
                } else {
                    stateLiveData.postValue(MovieCastState.Error(errorMessage ?: "Unknown error"))
                }
            }
        })
    }

    private fun castToUiStateContent(cast: MovieCast): MovieCastState {
        // Строим список элементов RecyclerView
        val items = buildList<MovieCastRVItem> {
            // Если есть хотя бы один режиссёр, добавим заголовок
            if (cast.directors.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Directors")
                this += cast.directors.map { MovieCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один сценарист, добавим заголовок
            if (cast.writers.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Writers")
                this += cast.writers.map { MovieCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один актёр, добавим заголовок
            if (cast.actors.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Actors")
                this += cast.actors.map { MovieCastRVItem.PersonItem(it) }
            }

            // Если есть хотя бы один дополнительный участник, добавим заголовок
            if (cast.others.isNotEmpty()) {
                this += MovieCastRVItem.HeaderItem("Others")
                this += cast.others.map { MovieCastRVItem.PersonItem(it) }
            }
        }

        return MovieCastState.Content(
            fullTitle = cast.fullTitle,
            items = items
        )
    }
}

