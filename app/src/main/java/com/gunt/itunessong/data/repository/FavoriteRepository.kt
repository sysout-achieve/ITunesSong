package com.gunt.itunessong.data.repository

import com.gunt.itunessong.data.domain.Track

interface FavoriteRepository {
    suspend fun getAll(): List<Track>

    suspend fun fetchFavoriteTrack(limit: Int, offset: Int): List<Track>

    suspend fun insertFavoriteTrack(track: Track)

    suspend fun deleteFavoriteTrack(track: Track)
}
