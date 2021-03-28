package com.gunt.itunessong.ui.favorite

import androidx.paging.PagedList
import com.google.common.truth.Truth
import com.gunt.itunessong.data.domain.Track
import com.gunt.itunessong.repository.FakeFavoriteRepository
import com.gunt.itunessong.ui.bind.TRACK_PAGING_ITEM_SIZE
import org.junit.Before
import org.junit.Test
import java.lang.reflect.Field

class FavoritesViewModelTest {
    lateinit var favoritesViewModel: FavoritesViewModel

    @Before
    fun setUp() {
        val list = mutableListOf<Track>()
        favoritesViewModel =
            FavoritesViewModel(FakeFavoriteRepository(list))
    }

    @Test
    fun countFetchingPageSizeTest() {
        // paging 호출 사이즈 == TRACK_PAGING_ITEM_SIZE가 같은지 테스트
        //  given
        val expected = TRACK_PAGING_ITEM_SIZE
        val field: Field = favoritesViewModel.javaClass.getDeclaredField("config")
        field.isAccessible = true
        val config: PagedList.Config = field.get(favoritesViewModel) as PagedList.Config

        //  then
        Truth.assertThat(config.pageSize).isEqualTo(expected)
    }
}
