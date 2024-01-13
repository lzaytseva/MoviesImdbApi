package com.github.lzaytseva.moviesimdbapi.ui.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.github.lzaytseva.moviesimdbapi.ui.about.AboutFragment
import com.github.lzaytseva.moviesimdbapi.ui.poster.PosterFragment

class DetailsPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val movieID: String,
    private val posterUrl: String
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PosterFragment.newInstance(posterUrl)
            else -> AboutFragment.newInstance(movieID)
        }
    }
}