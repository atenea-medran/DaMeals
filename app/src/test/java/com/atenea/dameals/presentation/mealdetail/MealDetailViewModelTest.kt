package com.atenea.dameals.presentation.mealdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.MealModelDataBuilder
import com.atenea.dameals.domain.usecase.GetMealDetailUseCase
import com.atenea.dameals.testutil.DefaultDispatcherRule
import com.atenea.dameals.testutil.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.hamcrest.CoreMatchers.`is`
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

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN detail viewModel getData EXPECT returns data`() = runTest {
        coEvery { getMealDetailUseCase.invoke("52772") } returns
                MealModelDataBuilder()

        val viewModel = MealDetailViewModel(getMealDetailUseCase)

        viewModel.getMeal("52772")

        val result = viewModel.meal.getOrAwaitValue()

        MatcherAssert.assertThat(result.idMeal, `is`("52772"))
    }
}