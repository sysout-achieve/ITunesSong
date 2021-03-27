package com.gunt.itunessong.di

import com.gunt.itunessong.data.mapper.TrackEntityMapper
import com.gunt.itunessong.data.mapper.TrackMapper
import com.gunt.itunessong.data.repository.SongRepository
import com.gunt.itunessong.data.repository.network.SongRepositoryRemote
import com.gunt.itunessong.data.repository.network.SongRepositoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSongRepository(
        songRepositoryService: SongRepositoryService,
        trackMapper: TrackMapper
    ): SongRepository {
        return SongRepositoryRemote(songRepositoryService, trackMapper)
    }

    @Singleton
    @Provides
    fun provideTrackMapper(): TrackMapper {
        return TrackMapper()
    }

}
