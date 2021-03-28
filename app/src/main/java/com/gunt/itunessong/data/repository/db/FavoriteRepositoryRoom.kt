package com.gunt.itunessong.data.repository.db

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.mapper.TrackEntityMapper
import com.gunt.itunessong.data.repository.FavoriteRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.reactivex.Completable
import io.reactivex.Single

@Module
@InstallIn(ActivityRetainedComponent::class)
class FavoriteRepositoryRoom
constructor(
    private var trackDao: TrackDao,
    private var trackEntityMapper: TrackEntityMapper
) : FavoriteRepository {
    override fun getAll(): Single<List<Track>> {
        return trackDao.getAll().map {
            trackEntityMapper.toDomainModelList(it)
        }
    }

    override  fun fetchFavoriteTrack(limit: Int, offset: Int): Single<List<Track>> {
        return trackDao.fetchTracks(limit, offset).map {
            trackEntityMapper.toDomainModelList(it)
        }
    }

    override fun insertFavoriteTrack(track: Track): Completable {
       return trackDao.insert(trackEntityMapper.mapFromDomainModel(track))
    }

    override fun deleteFavoriteTrack(track: Track):Completable {
        return trackDao.delete(trackEntityMapper.mapFromDomainModel(track))
    }
}
