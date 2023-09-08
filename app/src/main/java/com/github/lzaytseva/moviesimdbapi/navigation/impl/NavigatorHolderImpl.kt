package com.github.lzaytseva.moviesimdbapi.navigation.impl

import androidx.fragment.app.Fragment
import com.github.lzaytseva.moviesimdbapi.navigation.api.Navigator
import com.github.lzaytseva.moviesimdbapi.navigation.api.NavigatorHolder

class NavigatorHolderImpl: NavigatorHolder {
    private var navigator: Navigator? = null
    override fun attachNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    override fun detachNavigator() {
        this.navigator = null
    }

    override fun openFragment(fragment: Fragment) {
        navigator?.openFragment(fragment)
    }
}