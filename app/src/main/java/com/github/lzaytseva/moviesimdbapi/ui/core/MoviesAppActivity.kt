package com.github.lzaytseva.moviesimdbapi.ui.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.github.lzaytseva.moviesimdbapi.R
import com.github.lzaytseva.moviesimdbapi.navigation.api.NavigatorHolder
import com.github.lzaytseva.moviesimdbapi.navigation.impl.NavigatorImpl
import com.github.lzaytseva.moviesimdbapi.ui.movies.MoviesSearchFragment
import org.koin.android.ext.android.inject

class MoviesAppActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by inject()

    private val navigator = NavigatorImpl(
        fragmentContainerViewId = R.id.main_fragment_container,
        fragmentManager = supportFragmentManager
    )

    override fun onResume() {
        super.onResume()
        navigatorHolder.attachNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_app)

        if (savedInstanceState == null) {
            navigator.openFragment(MoviesSearchFragment.newInstance())
        }
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.detachNavigator()
    }
}