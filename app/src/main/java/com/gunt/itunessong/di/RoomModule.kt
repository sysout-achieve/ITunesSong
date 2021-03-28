package com.gunt.itunessong.di

import android.content.Context
import androidx.room.Room
import com.gunt.itunessong.data.mapper.TrackEntityMapper
import com.gunt.itunessong.data.repository.FavoriteRepository
import com.gunt.itunessong.data.repository.db.AppDatabase
import com.gunt.itunessong.data.repository.db.FavoriteRepositoryRoom
import com.gunt.itunessong.data.repository.db.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java
            , "database-iTunes"
        ).build()
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): TrackDao {
        return appDatabase.trackDAO()
    }

    @Singleton
    @Provides
    fun provideFavoriteRepository(
        trackDao: TrackDao,
        trackEntityMapper: TrackEntityMapper
    ): FavoriteRepository {
        return FavoriteRepositoryRoom(trackDao, trackEntityMapper)
    }

    @Singleton
    @Provides
    fun provideTrackEntityMapper(): TrackEntityMapper {
        return TrackEntityMapper()
    }
}
