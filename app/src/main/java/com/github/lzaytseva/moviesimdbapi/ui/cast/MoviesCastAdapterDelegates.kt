package com.github.lzaytseva.moviesimdbapi.ui.cast

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.github.lzaytseva.moviesimdbapi.databinding.ListItemCastBinding
import com.github.lzaytseva.moviesimdbapi.databinding.ListItemHeaderBinding
import com.github.lzaytseva.moviesimdbapi.ui.core.RVItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

// Делегат для заголовков на экране состава участников
fun movieCastHeaderDelegate() =
    adapterDelegateViewBinding<MovieCastRVItem.HeaderItem, RVItem, ListItemHeaderBinding>(
        { layoutInflater, root ->
            ListItemHeaderBinding.inflate(layoutInflater, root, false)
        }
    ) {
        bind { binding.headerTextView.text = item.headerText }
    }

// Делегат для отображения участников на соответствующем экране
fun movieCastPersonDelegate() =
    adapterDelegateViewBinding<MovieCastRVItem.PersonItem, RVItem, ListItemCastBinding>(
        { layoutInflater, root ->
            ListItemCastBinding.inflate(layoutInflater, root, false)
        }
    ) {
        bind {
            if (item.data.image == null) {
                binding.actorImageView.isVisible = false
            } else {
                Glide.with(itemView)
                    .load(item.data.image)
                    .into(binding.actorImageView)
                binding.actorImageView.isVisible = true
            }

            binding.actorNameTextView.text = item.data.name
            binding.actorDescriptionTextView.text = item.data.description
        }
    }