package com.gunt.itunessong.ui.bind

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gunt.itunessong.data.domain.Song

class TrackListAdapter(private val onClick: (Song?) -> Unit) : PagedListAdapter<Song, TrackViewHolder>(diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song) = oldItem.collectionId == newItem.collectionId
            override fun areContentsTheSame(oldItem: Song, newItem: Song) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder.from(parent, onClick)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}