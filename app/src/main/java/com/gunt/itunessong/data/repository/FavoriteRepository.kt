package com.gunt.itunessong.data.repository

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.network.response.ITunesResponse
import io.reactivex.rxjava3.core.Single

interface FavoriteRepository {
    fun fetchFavoriteTrack(limit: Int, offset: Int): Single<ITunesResponse<Track>>

    fun insertFavoriteTrack(track: Track, unit:()->Unit)

    fun deleteFavoriteTrack(track: Track, unit:()->Unit)
}