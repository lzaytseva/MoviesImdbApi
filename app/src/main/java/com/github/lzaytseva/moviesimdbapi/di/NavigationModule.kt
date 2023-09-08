package com.github.lzaytseva.moviesimdbapi.di

import com.github.lzaytseva.moviesimdbapi.navigation.api.Router
import com.github.lzaytseva.moviesimdbapi.navigation.impl.RouterImpl
import org.koin.dsl.module

val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigationHolder }
}