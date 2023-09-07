package com.github.lzaytseva.moviesimdbapi.ui.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PosterViewModel(posterUrl: String) : ViewModel() {

    private val urlLiveData = MutableLiveData(posterUrl)
    fun observeUrl(): LiveData<String> = urlLiveData

}