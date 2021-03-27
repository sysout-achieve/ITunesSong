package com.gunt.itunessong.ui.songs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gunt.itunessong.data.domain.Song
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.SongRepository
import com.gunt.itunessong.ui.bind.TrackDataService
import com.gunt.itunessong.ui.bind.TrackDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SongsViewModel
@Inject
constructor(
        private val songRepository: SongRepository
) : ViewModel(), TrackDataService {

    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    private val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(30)
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()

    val trackList = LivePagedListBuilder(
            object : DataSource.Factory<Int, Track>() {
                override fun create(): DataSource<Int, Track> {
                    return TrackDataSource(this@SongsViewModel)
                }
            },
            config
    ).build()

    override fun fetchSongs(limit: Int, offset: Int, unit: (List<Track>) -> Unit) {
        songRepository.fetchSongs(limit, offset)
                .subscribeOn(Schedulers.io())
                .subscribe({

                    //TODO : null safety 고려해야함
                    unit(it.results!!)
                }, {
                    it.printStackTrace()
                })
    }
}