package com.github.lzaytseva.moviesimdbapi.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.lzaytseva.moviesimdbapi.R
import com.github.lzaytseva.moviesimdbapi.databinding.FragmentMovieDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator

class MovieDetailsFragment : Fragment() {
    private lateinit var movieId: String
    private lateinit var posterUrl: String

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            movieId = it.getString(ARGS_ID).orEmpty()
            posterUrl = it.getString(ARGS_POSTER).orEmpty()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = DetailsPagerAdapter(
            childFragmentManager,
            lifecycle, movieId, posterUrl
        )

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.poster)
                1 -> tab.text = getString(R.string.details)
            }
        }
        tabMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabMediator.detach()
        _binding = null
    }

    companion object {
        private const val ARGS_ID = "id"
        private const val ARGS_POSTER = "poster"

        fun createArgs(movieId: String, posterUrl: String): Bundle {
            return bundleOf(
                ARGS_ID to movieId,
                ARGS_POSTER to posterUrl
            )
        }
    }
}