package com.gunt.itunessong.ui.bind

import androidx.paging.PageKeyedDataSource
import com.gunt.itunessong.data.domain.Track

const val TRACK_PAGING_ITEM_SIZE = 30
const val TRACK_INITIAL_ITEM_SIZE = 30

class TrackDataSource(private val trackDataService: TrackDataService) : PageKeyedDataSource<Int, Track>() {

    private var offset = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Track>) {
        trackDataService.fetchTracks(params.requestedLoadSize, offset) { response ->
            offset += params.requestedLoadSize
            callback.onResult(response, null, offset)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Track>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Track>) {
        trackDataService.fetchTracks(params.requestedLoadSize, params.key) { response ->
            offset += params.requestedLoadSize
            callback.onResult(response, offset)
        }
    }
}
