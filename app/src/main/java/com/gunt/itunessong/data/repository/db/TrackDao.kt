package com.gunt.itunessong.data.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gunt.itunessong.data.repository.db.entity.TrackEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TrackDao {

    @Query("SELECT * FROM TRACK ")
    fun getAll(): Single<List<TrackEntity>>

    @Query("SELECT * FROM TRACK LIMIT :limit OFFSET :offset")
    fun fetchTracks(limit: Int, offset: Int): Single<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(trackEntity: TrackEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracksAll(tracks: List<TrackEntity>)

    @Delete
    fun delete(trackEntity: TrackEntity): Completable

    @Query("DELETE FROM TRACK")
    fun deleteAll()
}
