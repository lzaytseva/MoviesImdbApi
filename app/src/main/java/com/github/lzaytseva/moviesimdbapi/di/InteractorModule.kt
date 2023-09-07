package com.github.lzaytseva.moviesimdbapi.di


import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesInteractor
import com.github.lzaytseva.moviesimdbapi.domain.impl.MoviesInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<MoviesInteractor> {
        MoviesInteractorImpl(
            repository = get()
        )
    }
}