package com.gunt.itunessong.data.repository.db

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.mapper.TrackEntityMapper
import com.gunt.itunessong.data.repository.FavoriteRepository
import com.gunt.itunessong.data.repository.network.response.ITunesResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
@InstallIn(ActivityRetainedComponent::class)
class FavoriteRepositoryRoom
constructor(
    private var trackDao: TrackDao,
    private var trackEntityMapper: TrackEntityMapper
) : FavoriteRepository {

    override fun fetchFavoriteTrack(limit: Int, offset: Int): Single<ITunesResponse<Track>> {
        return trackDao.getAll().map { ITunesResponse(it.size, trackEntityMapper.toDomainModelList(it)) }
//        val list = trackEntityMapper.toDomainModelList(trackDao.getAll())
//        return Single.just(ITunesResponse(list.size, list))
    }

    override fun insertFavoriteTrack(track: Track, unit:()->Unit) {
        Single.just(track)
            .subscribeOn(Schedulers.io())
            .subscribe({
                trackDao.insert(trackEntityMapper.mapFromDomainModel(it))
                unit()
            },{
                it.printStackTrace()
            })
    }

    override fun deleteFavoriteTrack(track: Track, unit:()->Unit) {
        Single.just(track)
            .subscribeOn(Schedulers.io())
            .subscribe({
                trackDao.delete(trackEntityMapper.mapFromDomainModel(it))
                unit()
            },{
                it.printStackTrace()
            })
    }
}