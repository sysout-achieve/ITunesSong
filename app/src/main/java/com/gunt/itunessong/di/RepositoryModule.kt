package com.gunt.itunessong.di

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
        songRepositoryService: SongRepositoryService
    ) : SongRepository{
        return SongRepositoryRemote(songRepositoryService)
    }
}