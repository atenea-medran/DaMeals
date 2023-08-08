package com.atenea.dameals.presentation.favoritemeallist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atenea.dameals.buildMealList
import com.atenea.dameals.data.mappers.toMealLocal
import com.atenea.dameals.domain.usecase.GetFavoriteMealListUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import com.atenea.dameals.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        coEvery { getFavoriteMealListUseCase.invoke() } returns
                flow { buildMealList(10) }

        val viewModel = FavoriteMealListViewModel(
            getFavoriteMealListUseCase,
            removeMealFromFavoriteUseCase
        )

        viewModel.getData()
        val result = viewModel.favoriteMealListFlow.value as? FavoriteMealListState.FavoriteMealList

        MatcherAssert.assertThat(result, CoreMatchers.`is`(nullValue()))

    }


}