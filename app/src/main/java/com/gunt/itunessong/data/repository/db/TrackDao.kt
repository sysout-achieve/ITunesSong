package com.gunt.itunessong.data.repository.db

import androidx.room.*
import com.gunt.itunessong.data.repository.db.entity.TrackEntity

@Dao
interface TrackDao {

    @Query("SELECT * FROM TRACK ")
    suspend fun getAll(): List<TrackEntity>

    @Query("SELECT * FROM TRACK LIMIT :limit OFFSET :offset")
    suspend fun fetchTracks(limit: Int, offset: Int): List<TrackEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(trackEntity: TrackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracksAll(tracks: List<TrackEntity>)

    @Delete
    suspend fun delete(trackEntity: TrackEntity)

    @Query("DELETE FROM TRACK")
    suspend fun deleteAll()
}
