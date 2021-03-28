package com.gunt.itunessong.ui.bind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunt.itunessong.R
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.databinding.ItemSongBinding
import com.gunt.itunessong.ui.MainViewModel.Companion.checkFavorite

class TrackViewHolder
constructor
(private val binding: ItemSongBinding, private val onClick: (Track?, Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Track) {
        if (checkFavorite(item)) binding.imgStar.setImageResource(R.drawable.ic_like_star)
        else binding.imgStar.setImageResource(R.drawable.ic_empty_star)
        binding.track = item
        binding.imgStar.setOnClickListener {
            onClick(item, adapterPosition)
        }
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (Track?, Int) -> Unit): TrackViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSongBinding.inflate(layoutInflater, parent, false)
            return TrackViewHolder(binding, onClick)
        }
    }
}
