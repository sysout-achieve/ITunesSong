package com.gunt.itunessong.repository

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.FavoriteRepository
import io.reactivex.Completable
import io.reactivex.Single

class FakeFavoriteRepository
constructor(private var list: MutableList<Track>) : FavoriteRepository {

    override fun getAll(): Single<List<Track>> {
        return Single.just(list)
    }

    override fun fetchFavoriteTrack(limit: Int, offset: Int): Single<List<Track>> {
        return Single.just(list.subList(offset, limit))
    }

    override fun insertFavoriteTrack(track: Track): Completable {
        list.add(track)
        return Completable.complete()
    }

    override fun deleteFavoriteTrack(track: Track): Completable {
        list.remove(track)
        return Completable.complete()
    }
}
