package com.gunt.itunessong.repository

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.SongRepository
import com.gunt.itunessong.data.repository.network.response.ITunesResponse
import io.reactivex.rxjava3.core.Single

class FakeSongRepository
constructor(private var list: MutableList<Track>) : SongRepository {

    override fun fetchSongs(limit: Int, offset: Int): Single<ITunesResponse<Track>> {
        return Single.just(ITunesResponse(list.size, list))
    }
}