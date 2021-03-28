package com.gunt.itunessong.ui.songs

import org.junit.Before
import org.junit.Test

class SongsViewModelTest {

    lateinit var songsViewModel: SongsViewModel

    @Before
    fun setUp() {
//        songsViewModel = SongsViewModel(
//            SongRepositoryRemote(
//                RetrofitModule.provideRetrofitApiService(RetrofitModule.provideOkHttpClient()),
//                RepositoryModule.provideTrackMapper()
//            )
//        ,FavoriteRepositoryRoom(RoomModule.)
//        )
    }

    @Test
    fun fetchSongsTest() {
        // given
        var size = 0
        var leng = 0

        // when
        songsViewModel.fetchTracks(30, 0) {
//            size = it.size!!
//            leng = it.results!!.size
        }

        // then
        Thread.sleep(200000)
    }
}
