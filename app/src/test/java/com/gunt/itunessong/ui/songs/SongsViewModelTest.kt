package com.gunt.itunessong.ui.songs

import com.gunt.itunessong.data.repository.network.SongRepositoryRemote
import com.gunt.itunessong.di.RetrofitModule
import org.junit.Before
import org.junit.Test

class SongsViewModelTest{

    lateinit var songsViewModel: SongsViewModel

    @Before
    fun setUp() {
        songsViewModel = SongsViewModel(
            SongRepositoryRemote(
                RetrofitModule.provideRetrofitApiService(RetrofitModule.provideOkHttpClient())
            )
        )
    }

    @Test
    fun fetchSongsTest() {
        // given
        var size = 0
        var leng = 0


        // when
        songsViewModel.fetchSongs(30, 0) {
            size = it.resultCount!!
            leng = it.results!!.size
        }

        // then
        Thread.sleep(200000)
    }
}