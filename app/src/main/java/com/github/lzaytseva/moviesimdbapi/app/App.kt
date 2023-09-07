package com.github.lzaytseva.moviesimdbapi.app

import android.app.Application
import com.github.lzaytseva.moviesimdbapi.di.dataModule
import com.github.lzaytseva.moviesimdbapi.di.interactorModule
import com.github.lzaytseva.moviesimdbapi.di.repositoryModule
import com.github.lzaytseva.moviesimdbapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}