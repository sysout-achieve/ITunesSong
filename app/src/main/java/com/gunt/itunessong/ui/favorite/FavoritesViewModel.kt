package com.gunt.itunessong.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.FavoriteRepository
import com.gunt.itunessong.ui.MainViewModel
import com.gunt.itunessong.ui.bind.TRACK_INITIAL_ITEM_SIZE
import com.gunt.itunessong.ui.bind.TRACK_PAGING_ITEM_SIZE
import com.gunt.itunessong.ui.bind.TrackDataService
import com.gunt.itunessong.ui.bind.TrackDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel
@Inject
constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel(), TrackDataService {

    val updatedPosition = MutableLiveData<Int>()

    private val config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(TRACK_INITIAL_ITEM_SIZE)
        .setPageSize(TRACK_PAGING_ITEM_SIZE)
        .setEnablePlaceholders(false)
        .build()

    val trackList = LivePagedListBuilder(
        object : DataSource.Factory<Int, Track>() {
            override fun create(): DataSource<Int, Track> {
                return TrackDataSource(this@FavoritesViewModel)
            }
        },
        config
    ).build()

    override fun fetchTracks(limit: Int, offset: Int, unit: (List<Track>) -> Unit) {
        favoriteRepository.fetchFavoriteTrack(limit, offset)
            .subscribeOn(Schedulers.io())
            .subscribe({
                unit(it)
            }, {
                Timber.d(it)
            })
    }

    fun insertFavorite(track: Track, position: Int) {
        favoriteRepository.insertFavoriteTrack(track)
            .subscribeOn(Schedulers.io())
            .subscribe {
                MainViewModel.insertFavorite(track)
                updateTargetPosition(position)
            }
    }


    fun deleteFavorite(track: Track, position: Int) {
        favoriteRepository.deleteFavoriteTrack(track)
            .subscribeOn(Schedulers.io())
            .subscribe {
                MainViewModel.deleteFavorite(track)
                updateTargetPosition(position)
            }
    }

    private fun updateTargetPosition(position: Int) {
        updatedPosition.postValue(position)
    }
}
