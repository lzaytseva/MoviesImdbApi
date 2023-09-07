package com.github.lzaytseva.moviesimdbapi.di

import android.content.Context
import com.github.lzaytseva.moviesimdbapi.data.network.IMDbApiService
import com.github.lzaytseva.moviesimdbapi.data.network.NetworkClient
import com.github.lzaytseva.moviesimdbapi.data.network.RetrofitNetworkClient
import com.github.lzaytseva.moviesimdbapi.data.storage.LocalStorage
import com.github.lzaytseva.moviesimdbapi.data.storage.Storage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<IMDbApiService> {
        Retrofit.Builder()
            .baseUrl(RetrofitNetworkClient.IMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApiService::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(
            context = androidContext(),
            imdbService = get()
        )
    }

    single<Storage> {
        LocalStorage(
            sharedPreferences = get()
        )
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

}