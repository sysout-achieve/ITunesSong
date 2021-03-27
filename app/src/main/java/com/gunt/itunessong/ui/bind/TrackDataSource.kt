package com.gunt.itunessong.ui.bind

import androidx.paging.PageKeyedDataSource
import com.gunt.itunessong.data.domain.Track

class TrackDataSource(private val trackDataService: TrackDataService) : PageKeyedDataSource<Int, Track>() {

    private var offset = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Track>) {
        trackDataService.fetchSongs(params.requestedLoadSize, offset) { response ->
            offset += params.requestedLoadSize
            callback.onResult(response, null, offset)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Track>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Track>) {
        trackDataService.fetchSongs(params.requestedLoadSize, params.key) { response ->
            offset += params.requestedLoadSize
            callback.onResult(response, offset)
        }
    }
}