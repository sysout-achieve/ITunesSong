package com.gunt.itunessong

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.gunt.itunessong.data.repository.db.AppDatabase
import com.gunt.itunessong.data.repository.db.entity.TrackEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class RoomRepositoryDaoTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private lateinit var appDatabase: AppDatabase

    private val trackEntity =  TrackEntity(
        trackId = 1,
        artistName = "greenday",
        collectionName = "greendayCollection",
        trackName = "greendayTrackName",
        artworkUrl60 = "https://is2-ssl.mzstatic.com/image/thumb/Music114/v4/77/4b/20/774b2007-2198-8fa3-1491-8c0e3b0ce4f2/source/60x60bb.jpg"
    )

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
    }

    @Test
    fun trackDao1InsertTest(){
        //when
        appDatabase.trackDAO().insert(trackEntity)

        //then
        assertThat(appDatabase.trackDAO().getAll().blockingGet()).isEqualTo(trackEntity)
    }

    @Test
    fun trackDao2DeleteTest(){
        //given
        // trackDao1InsertTest()
        //when
        appDatabase.trackDAO().delete(trackEntity)

        //then
        assertThat(appDatabase.trackDAO().getAll().blockingGet()).isEmpty()
    }

    @Test
    fun trackDao3InsertListTest(){
        //given
        val expected = 31
        val list = mutableListOf<TrackEntity>()
        for (i in 1..expected){
            list.add(
                TrackEntity(
                    trackId = i.toLong(),
                    artistName = "greenday",
                    collectionName = "greendayCollection",
                    trackName = "greendayTrackName",
                    artworkUrl60 = "https://is2-ssl.mzstatic.com/image/thumb/Music114/v4/77/4b/20/774b2007-2198-8fa3-1491-8c0e3b0ce4f2/source/60x60bb.jpg"
                )
            )
        }
        //when
        appDatabase.trackDAO().insertTracksAll(list)

        //then
        val resultList = appDatabase.trackDAO().getAll().blockingGet()
        assertThat(resultList.size).isNotEqualTo(0)
        assertThat(resultList.size).isEqualTo(expected)
    }

    @Test
    fun trackDao4FetchTest(){
        //given
        val listInsert = mutableListOf<TrackEntity>()
        for (i in 1..31){
            listInsert.add(
                TrackEntity(
                    trackId = i.toLong(),
                    artistName = "greenday",
                    collectionName = "greendayCollection",
                    trackName = "greendayTrackName",
                    artworkUrl60 = "https://is2-ssl.mzstatic.com/image/thumb/Music114/v4/77/4b/20/774b2007-2198-8fa3-1491-8c0e3b0ce4f2/source/60x60bb.jpg"
                )
            )
        }
        appDatabase.trackDAO().insertTracksAll(listInsert)

        //when
        val list = appDatabase.trackDAO().fetchTracks(10,0).blockingGet()

        //then
        assertThat(list.size).isEqualTo(10)

        // 저장된 31개의 데이터 중에 limit 10 offset 30일 경우 -> 기대값 = 1
        val expected = 1
        val list2 = appDatabase.trackDAO().fetchTracks(10,30).blockingGet()
        assertThat(list2.size).isEqualTo(expected)
    }

    @After
    fun cleanup(){
        appDatabase.trackDAO().deleteAll()
    }
}