package com.gunt.itunessong.data.repository.network

import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.mapper.TrackMapper
import com.gunt.itunessong.data.repository.SongRepository
import com.gunt.itunessong.data.repository.network.response.ITunesResponse
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import io.reactivex.rxjava3.core.Single

@Module
@InstallIn(ActivityRetainedComponent::class)
class SongRepositoryRemote
constructor(
    private var songRepositoryService: SongRepositoryService,
    private var trackMapper: TrackMapper
) : SongRepository {

    override fun fetchSongs(limit: Int, offset: Int): Single<ITunesResponse<Track>> {
        return songRepositoryService.fetchSongs(limit = limit, offset = offset).map {
            ITunesResponse(
                it.resultCount,
                trackMapper.toDomainModelList(it.results!!)
            )
        }
    }
}