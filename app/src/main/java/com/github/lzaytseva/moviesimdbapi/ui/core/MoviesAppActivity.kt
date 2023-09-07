package com.github.lzaytseva.moviesimdbapi.ui.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.github.lzaytseva.moviesimdbapi.R
import com.github.lzaytseva.moviesimdbapi.ui.movies.MoviesSearchFragment

class MoviesAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_app)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.main_fragment_container, MoviesSearchFragment.newInstance())
            }
        }
    }
}