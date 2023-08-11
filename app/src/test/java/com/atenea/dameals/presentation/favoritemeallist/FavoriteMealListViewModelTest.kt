package com.atenea.dameals.presentation.favoritemeallist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.atenea.dameals.buildMealList
import com.atenea.dameals.domain.usecase.GetFavoriteMealListUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import com.atenea.dameals.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class FavoriteMealListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val defaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getFavoriteMealListUseCase: GetFavoriteMealListUseCase

    @MockK(relaxed = true)
    private lateinit var removeMealFromFavoriteUseCase: RemoveMealFromFavoriteUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN viewModel init EXPECT data at LiveData`() = runTest {
        val list = buildMealList(10)
        coEvery { getFavoriteMealListUseCase.invoke() } returns flow {
            emit(list)
        }

        val viewModel = FavoriteMealListViewModel(
            getFavoriteMealListUseCase,
            removeMealFromFavoriteUseCase
        )

        viewModel.favoriteMealListFlow.test {
            viewModel.getData()
            assertEquals(FavoriteMealListState.Idle, awaitItem())
            assertEquals(FavoriteMealListState.FavoriteMealList(list), awaitItem())
        }

        assertThat(FavoriteMealListState.FavoriteMealList(list).favoriteMealList.size, `is`(10))

    }


}