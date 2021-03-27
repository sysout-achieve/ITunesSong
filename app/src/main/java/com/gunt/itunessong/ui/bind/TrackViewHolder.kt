package com.gunt.itunessong.ui.bind

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunt.itunessong.data.domain.Song
import com.gunt.itunessong.databinding.ItemSongBinding

class TrackViewHolder
constructor
(private val binding: ItemSongBinding, private val onClick: (Song?) -> Unit)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Song?) {
        binding.song = item
        binding.imgStar.setOnClickListener {
            onClick(item)
        }
    }

    companion object {
        fun from(parent: ViewGroup, onClick: (Song?) -> Unit): TrackViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSongBinding.inflate(layoutInflater, parent, false)
            return TrackViewHolder(binding, onClick)
        }
    }
}