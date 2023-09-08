package com.github.lzaytseva.moviesimdbapi.navigation.api

import androidx.fragment.app.Fragment

interface Router {
    val navigationHolder: NavigatorHolder
    fun openFragment(fragment: Fragment)
}