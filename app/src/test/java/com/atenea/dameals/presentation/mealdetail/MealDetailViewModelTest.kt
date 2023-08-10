package com.atenea.dameals.presentation.mealdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.mealModelDataBuilder
import com.atenea.dameals.domain.usecase.GetMealDetailUseCase
import com.atenea.dameals.domain.usecase.MakeMealFavoriteUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import com.atenea.dameals.testutil.DefaultDispatcherRule
import com.atenea.dameals.testutil.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealDetailViewModelTest {
    @get:Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val defaultDispatcherRule: DefaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var getMealDetailUseCase: GetMealDetailUseCase
    @MockK(relaxed = true)
    private lateinit var makeMealFavoriteUseCase: MakeMealFavoriteUseCase
    @MockK(relaxed = true)
    private lateinit var removeMealFromFavoriteUseCase: RemoveMealFromFavoriteUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN detail viewModel getData EXPECT returns data`() = runTest {
        coEvery { getMealDetailUseCase.invoke("52772") } returns
                mealModelDataBuilder()

        val viewModel = MealDetailViewModel(
            getMealDetailUseCase,
            makeMealFavoriteUseCase,
            removeMealFromFavoriteUseCase
        )
        viewModel.getMeal("52772")

        val result = viewModel.meal.getOrAwaitValue()

        assertThat(result?.idMeal, `is`("52772"))
    }

    @Test
    fun `WHEN detail viewModel getData with wrong id EXPECT returns data`() = runTest {
        coEvery { getMealDetailUseCase.invoke("5277221312") } returns
                mealModelDataBuilder()

        val viewModel = MealDetailViewModel(
            getMealDetailUseCase,
            makeMealFavoriteUseCase,
            removeMealFromFavoriteUseCase
        )

        viewModel.getMeal("52772")

        val result = viewModel.meal.getOrAwaitValue()

        assertThat(result?.idMeal, `is`(""))
    }
}