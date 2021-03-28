package com.gunt.itunessong.data.repository.db

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.mapper.TrackEntityMapper
import com.gunt.itunessong.data.repository.FavoriteRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class FavoriteRepositoryRoom
constructor(
    private var trackDao: TrackDao,
    private var trackEntityMapper: TrackEntityMapper
) : FavoriteRepository {
    override suspend fun getAll(): List<Track> {
        return trackDao.getAll().map {
            trackEntityMapper.mapToDomainModel(it)
        }
    }

    override suspend fun fetchFavoriteTrack(limit: Int, offset: Int): List<Track> {
        return trackDao.fetchTracks(limit, offset).map {
            trackEntityMapper.mapToDomainModel(it)
        }
    }

    override suspend fun insertFavoriteTrack(track: Track) {
        trackDao.insert(trackEntityMapper.mapFromDomainModel(track))
    }

    override suspend fun deleteFavoriteTrack(track: Track) {
        trackDao.delete(trackEntityMapper.mapFromDomainModel(track))
    }
}
