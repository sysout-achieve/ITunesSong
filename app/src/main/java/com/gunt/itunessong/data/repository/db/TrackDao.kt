package com.gunt.itunessong.data.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gunt.itunessong.data.repository.db.entity.TrackEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface TrackDao {

    @Query("SELECT * From TrackEntity ")
    fun getAll(): Single<List<TrackEntity>>

    @Insert
    fun insert(trackEntity: TrackEntity)

    @Delete
    fun delete(trackEntity: TrackEntity)
}
