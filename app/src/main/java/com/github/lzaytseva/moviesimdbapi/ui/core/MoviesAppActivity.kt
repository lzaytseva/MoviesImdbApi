package com.github.lzaytseva.moviesimdbapi.ui.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.lzaytseva.moviesimdbapi.R

class MoviesAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_app)
    }
}