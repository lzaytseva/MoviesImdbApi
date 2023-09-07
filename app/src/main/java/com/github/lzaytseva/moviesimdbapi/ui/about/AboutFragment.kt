package com.github.lzaytseva.moviesimdbapi.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.github.lzaytseva.moviesimdbapi.databinding.FragmentAboutBinding
import com.github.lzaytseva.moviesimdbapi.domain.model.MovieDetails
import com.github.lzaytseva.moviesimdbapi.ui.cast.MovieCastActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private val movieId by lazy {
        requireArguments().getString(KEY_ID)
    }

    private val aboutViewModel: AboutViewModel by viewModel {
        parametersOf(movieId)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aboutViewModel.observeState().observe(viewLifecycleOwner) {
            when (it) {
                is AboutState.Content -> showDetails(it.movie)
                is AboutState.Error -> showErrorMessage(it.message)
            }
        }

        binding.btnShowCast.setOnClickListener {
            MovieCastActivity.newInstance(requireContext(), movieId ?: "").apply {
                startActivity(this)
            }
        }
    }

    private fun showErrorMessage(message: String) {
        binding.apply {
            details.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = message
        }
    }

    private fun showDetails(movieDetails: MovieDetails) {
        binding.apply {
            details.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            with(movieDetails) {
                binding.title.text = title
                ratingValue.text = imDbRating
                yearValue.text = year
                countryValue.text = countries
                genreValue.text = genres
                directorValue.text = directors
                writerValue.text = writers
                castValue.text = stars
                binding.plot.text = plot
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val KEY_ID = "key_id"
        fun newInstance(id: String): AboutFragment {
            return AboutFragment().apply {
                arguments = bundleOf(KEY_ID to id)
            }
        }
    }
}