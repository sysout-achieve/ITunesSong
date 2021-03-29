package com.gunt.itunessong.ui

import androidx.lifecycle.ViewModel
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
        getAllFavorites()
    }

    companion object {
        // Favorites에 저장된 ID값을 HashMap 으로 저장(저장되어있을 경우 true, 없을 경우 false)
        private var hashMapFavorites = HashMap<Long, Boolean>()

        fun insertFavorite(track: Track) {
            hashMapFavorites[track.trackId] = true
        }

        fun deleteFavorite(track: Track) {
            hashMapFavorites.remove(track.trackId)
        }

        fun checkFavorite(track: Track): Boolean {
            return hashMapFavorites[track.trackId] ?: false
        }
    }

    private fun getAllFavorites() {
        compositeDisposable.add(
            favoriteRepository.getAll()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        result.forEach { hashMapFavorites[it.trackId] = true }
                    },
                    {
                        Timber.d(it)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
