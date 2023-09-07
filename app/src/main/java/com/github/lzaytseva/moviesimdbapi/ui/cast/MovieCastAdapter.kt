package com.github.lzaytseva.moviesimdbapi.ui.cast

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.lzaytseva.moviesimdbapi.R

class MoviesCastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Поменяли тип элементов на общий
    var items = emptyList<MovieCastRVItem>()

    // Возвращаем нужный ViewType в зависимости
    // от типа элементов списка
    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is MovieCastRVItem.HeaderItem -> R.layout.list_item_header
            is MovieCastRVItem.PersonItem -> R.layout.list_item_cast
        }
    }

    // Возвращаем нужный ViewHolder в зависимости
    // от viewType
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.list_item_header -> MovieCastHeaderViewHolder(parent)
            R.layout.list_item_cast -> MovieCastViewHolder(parent)
            else -> error("Unknown viewType create [$viewType]")
        }

    // Биндим ViewHolder корректно, в зависимости
    // от viewType
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            R.layout.list_item_header -> {
                val headerHolder = holder as MovieCastHeaderViewHolder
                headerHolder.bind(items[position] as MovieCastRVItem.HeaderItem)
            }

            R.layout.list_item_cast -> {
                val headerHolder = holder as MovieCastViewHolder
                headerHolder.bind(items[position] as MovieCastRVItem.PersonItem)
            }

            else -> error("Unknown viewType bind [${holder.itemViewType}]")
        }
    }

    override fun getItemCount(): Int = items.size

    class MovieCastViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_cast, parent, false)
        ) {

        var actorImage: ImageView = itemView.findViewById(R.id.actorImageView)
        var personName: TextView = itemView.findViewById(R.id.actorNameTextView)
        var personDescription: TextView = itemView.findViewById(R.id.actorDescriptionTextView)

        fun bind(personItem: MovieCastRVItem.PersonItem) {
            if (personItem.data.image == null) {
                actorImage.isVisible = false
            } else {
                Glide.with(itemView)
                    .load(personItem.data.image)
                    .into(actorImage)
                actorImage.isVisible = true
            }

            personName.text = personItem.data.name
            personDescription.text = personItem.data.description
        }
    }

    class MovieCastHeaderViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_header, parent, false)
        ) {

        var headerTextView: TextView = itemView.findViewById(R.id.headerTextView)

        fun bind(item: MovieCastRVItem.HeaderItem) {
            headerTextView.text = item.headerText
        }

    }

}