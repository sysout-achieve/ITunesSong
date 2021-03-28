package com.gunt.itunessong.data.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gunt.itunessong.data.repository.db.entity.TrackEntity

@Dao
interface TrackDao {

    @Query("SELECT * From TrackEntity ")
    suspend fun getAll(): List<TrackEntity>

    @Query("SELECT * FROM TrackEntity LIMIT :limit OFFSET :offset")
    suspend fun fetchTracks(limit: Int, offset: Int): List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trackEntity: TrackEntity)

    @Delete
    suspend fun delete(trackEntity: TrackEntity)
}
