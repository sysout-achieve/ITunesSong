package com.gunt.itunessong.data.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gunt.itunessong.data.repository.db.entity.TrackEntity

@Database(entities = [TrackEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackDAO(): TrackDao
}
