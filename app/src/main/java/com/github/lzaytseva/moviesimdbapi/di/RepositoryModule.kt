package com.github.lzaytseva.moviesimdbapi.di


import com.github.lzaytseva.moviesimdbapi.data.repository.MoviesRepositoryImpl
import com.github.lzaytseva.moviesimdbapi.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(
            networkClient = get(),
            localStorage = get(),
            castMapper = get()
        )
    }
}