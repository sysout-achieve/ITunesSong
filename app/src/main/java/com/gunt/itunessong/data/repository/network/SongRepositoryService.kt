package com.gunt.itunessong.data.repository.network

import com.gunt.itunessong.data.repository.network.dto.TrackDto
import com.gunt.itunessong.data.repository.network.response.ITunesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SongRepositoryService {

    @GET("/search")
    fun fetchSongs(
        @Query("term") term: String = "greenday",
        @Query("entity") entity: String = "song",
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Single<ITunesResponse<TrackDto>>
}
