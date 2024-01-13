package com.github.lzaytseva.moviesimdbapi.di


import com.github.lzaytseva.moviesimdbapi.ui.about.AboutViewModel
import com.github.lzaytseva.moviesimdbapi.ui.cast.MovieCastViewModel
import com.github.lzaytseva.moviesimdbapi.ui.movies.MoviesSearchViewModel
import com.github.lzaytseva.moviesimdbapi.ui.poster.PosterViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(
            application = androidApplication(),
            moviesInteractor = get()
        )
    }

    viewModel { (posterUrl: String) ->
        PosterViewModel(posterUrl)
    }

    viewModel { (movieId: String) ->
        AboutViewModel(
            movieId = movieId,
            moviesInteractor = get()
        )
    }

    viewModel { (movieId: String) ->
        MovieCastViewModel(
            movieId = movieId,
            moviesInteractor = get()
        )
    }
}