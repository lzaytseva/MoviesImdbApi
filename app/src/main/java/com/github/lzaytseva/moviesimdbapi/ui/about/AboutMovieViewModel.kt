package com.github.lzaytseva.moviesimdbapi.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesInteractor
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails


class AboutViewModel(
    private val movieId: String,
    private val moviesInteractor: MoviesInteractor,
) : ViewModel() {

    private val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {
        moviesInteractor.getMovieDetails(movieId, object : MoviesInteractor.MovieDetailsConsumer {

            override fun consume(info: MovieDetails?, errorMessage: String?) {
                if (info != null) {
                    stateLiveData.postValue(AboutState.Content(info))
                } else {
                    stateLiveData.postValue(AboutState.Error(errorMessage ?: "Unknown error"))
                }
            }
        })
    }

}