package com.gunt.itunessong.ui.bind

import com.gunt.itunessong.data.domain.Song

interface TrackDataService {
    fun fetchSongs(limit: Int, offset: Int, unit: (List<Song>) -> Unit)
}