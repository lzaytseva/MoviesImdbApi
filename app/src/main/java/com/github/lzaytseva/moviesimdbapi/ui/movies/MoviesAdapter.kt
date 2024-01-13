package com.github.lzaytseva.moviesimdbapi.ui.movies

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.lzaytseva.moviesimdbapi.R
import com.github.lzaytseva.moviesimdbapi.domain.model.Movie

class MoviesAdapter(private val clickListener: MovieClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent, clickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))

    }

    class MovieViewHolder(parent: ViewGroup, private val clickListener: MovieClickListener) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        ) {

        var title: TextView = itemView.findViewById(R.id.title)
        private var description: TextView = itemView.findViewById(R.id.description)
        private var poster: ImageView = itemView.findViewById(R.id.poster)
        private var inFavoriteToggle: ImageView = itemView.findViewById(R.id.favourite)

        fun bind(movie: Movie) {
            title.text = movie.title
            description.text = movie.description
            Glide.with(itemView)
                .load(movie.image)
                .into(poster)

            inFavoriteToggle.setImageDrawable(getFavoriteToggleDrawable(movie.inFavourite))

            itemView.setOnClickListener { clickListener.onMovieClick(movie) }

            inFavoriteToggle.setOnClickListener { clickListener.onFavoriteToggleClick(movie) }
        }

        private fun getFavoriteToggleDrawable(inFavorite: Boolean): Drawable? {
            return AppCompatResources.getDrawable(
                itemView.context,
                if (inFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
            )
        }
    }

    interface MovieClickListener {
        fun onMovieClick(movie: Movie)
        fun onFavoriteToggleClick(movie: Movie)
    }
}