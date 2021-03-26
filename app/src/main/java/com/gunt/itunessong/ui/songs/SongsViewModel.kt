package com.gunt.itunessong.ui.songs

import androidx.lifecycle.ViewModel
import com.gunt.itunessong.data.domain.Song
import com.gunt.itunessong.data.repository.SongRepository
import com.gunt.itunessong.data.repository.network.response.ITunesResponse
import dagger.hilt.android.scopes.ViewModelScoped

class SongsViewModel
@ViewModelScoped
constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    fun fetchSongs(limit: Int, offset: Int, unit: (ITunesResponse<Song>) -> Unit) {
        songRepository.fetchSongs(limit, offset).subscribe({
            unit(it)
        }, {
            it.printStackTrace()
        })
    }
}