package com.gunt.itunessong.data.repository.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackEntity(
    @PrimaryKey val trackId: Long,
    val artistName: String?,
    val collectionName: String?,
    val trackName: String?,
    val artworkUrl60: String?
)
