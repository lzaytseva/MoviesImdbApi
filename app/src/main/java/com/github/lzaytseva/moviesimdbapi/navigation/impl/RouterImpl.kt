package com.github.lzaytseva.moviesimdbapi.navigation.impl

import androidx.fragment.app.Fragment
import com.github.lzaytseva.moviesimdbapi.navigation.api.NavigatorHolder
import com.github.lzaytseva.moviesimdbapi.navigation.api.Router

class RouterImpl: Router {
    override val navigationHolder: NavigatorHolder = NavigatorHolderImpl()
    override fun openFragment(fragment: Fragment) {
        navigationHolder.openFragment(fragment)
    }

}