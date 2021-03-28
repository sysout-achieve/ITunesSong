package com.gunt.itunessong.ui.bind

import com.gunt.itunessong.data.domain.Track

interface TrackDataService {
    fun fetchTracks(limit: Int, offset: Int, unit: (List<Track>) -> Unit)
}
