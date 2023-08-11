package com.atenea.dameals.presentation.meallist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.buildMealList
import com.atenea.dameals.domain.usecase.GetMealListUseCase
import com.atenea.dameals.domain.usecase.MakeMealFavoriteUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCaseTest
import com.atenea.dameals.testutil.DefaultDispatcherRule
import com.atenea.dameals.testutil.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val defaultDispatcherRule: DefaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getMealListUseCase: GetMealListUseCase
    @MockK(relaxed = true)
    private lateinit var makeMealFavoriteUseCase: MakeMealFavoriteUseCase
    @MockK(relaxed = true)
    private lateinit var removeMealFromFavoriteUseCase: RemoveMealFromFavoriteUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN viewModel init EXPECT data at LiveData`() = runTest {
        coEvery { getMealListUseCase.invoke() } returns buildMealList(12)

        val viewModel = MealListViewModel(
            getMealListUseCase,
            makeMealFavoriteUseCase,
            removeMealFromFavoriteUseCase
        )

        viewModel.getData()

        val result = viewModel.mealList.getOrAwaitValue()

        MatcherAssert.assertThat(result?.size , CoreMatchers.`is`(12))

    }
}