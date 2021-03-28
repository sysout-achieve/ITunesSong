package com.gunt.itunessong.ui.songs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.FavoriteRepository
import com.gunt.itunessong.data.repository.SongRepository
import com.gunt.itunessong.ui.MainViewModel
import com.gunt.itunessong.ui.bind.TRACK_INITIAL_ITEM_SIZE
import com.gunt.itunessong.ui.bind.TRACK_PAGING_ITEM_SIZE
import com.gunt.itunessong.ui.bind.TrackDataService
import com.gunt.itunessong.ui.bind.TrackDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel
@Inject
constructor(
    private val songRepository: SongRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel(), TrackDataService {

    val updatedPosition = MutableLiveData<Int>()
    val loading = MutableLiveData(false)

    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(TRACK_INITIAL_ITEM_SIZE)
        .setPageSize(TRACK_PAGING_ITEM_SIZE)
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

    override fun fetchTracks(limit: Int, offset: Int, unit: (List<Track>) -> Unit) {
        loading.postValue(true)
        songRepository.fetchSongs(limit, offset)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    unit(it.results!!)
                    loading.postValue(false)
                },
                {
                    it.printStackTrace()
                    loading.postValue(false)
                }
            )
    }

    fun insertFavorite(track: Track, position: Int) {
        viewModelScope.launch {
            val deferred = CoroutineScope(Dispatchers.IO).async {
                favoriteRepository.insertFavoriteTrack(track)
                MainViewModel.insertFavorite(track)
            }
            deferred.await()
            updateTargetPosition(position)
        }
    }

    fun deleteFavorite(track: Track, position: Int) {
        viewModelScope.launch {
            val deferred = CoroutineScope(Dispatchers.IO).async {
                favoriteRepository.deleteFavoriteTrack(track)
                MainViewModel.deleteFavorite(track)
            }
            deferred.await()
            updateTargetPosition(position)
        }
    }

    private fun updateTargetPosition(position: Int) {
        updatedPosition.postValue(position)
    }
}
