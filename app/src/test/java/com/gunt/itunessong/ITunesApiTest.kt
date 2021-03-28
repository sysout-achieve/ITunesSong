package com.gunt.itunessong

import com.google.common.truth.Truth.assertThat
import com.gunt.itunessong.data.repository.network.SongRepositoryRemote
import com.gunt.itunessong.di.RepositoryModule
import com.gunt.itunessong.di.RetrofitModule
import com.gunt.itunessong.repository.FakeFavoriteRepository
import com.gunt.itunessong.ui.songs.SongsViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ITunesApiTest {

    lateinit var songsViewModel: SongsViewModel

    @Before
    fun setUp() {
        songsViewModel = SongsViewModel(
            SongRepositoryRemote(
                RetrofitModule.provideRetrofitApiService(RetrofitModule.provideOkHttpClient()),
                RepositoryModule.provideTrackMapper()
            ),
            FakeFavoriteRepository(mutableListOf())
        )
    }

    @Test
    fun fetchApiTest() = runBlocking {
        val expected = 30

        // when
        val result = songsViewModel.getFetchSongs(expected, 0).blockingGet()

        // then
        assertThat(result.resultCount).isEqualTo(expected)
        assertThat(result.results?.size).isEqualTo(expected)
    }
}
