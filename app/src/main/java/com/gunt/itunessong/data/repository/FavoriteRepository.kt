package com.gunt.itunessong.data.repository

import com.gunt.itunessong.data.domain.Track
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteRepository {
    fun getAll(): Single<List<Track>>

    fun fetchFavoriteTrack(limit: Int, offset: Int): Single<List<Track>>

    fun insertFavoriteTrack(track: Track): Completable

    fun deleteFavoriteTrack(track: Track):Completable
}
