package com.gunt.itunessong.data.repository

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.network.response.ITunesResponse
import io.reactivex.rxjava3.core.Single

interface SongRepository {
    fun fetchSongs(limit: Int, offset: Int): Single<ITunesResponse<Track>>
}
