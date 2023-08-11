package com.atenea.dameals.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.buildMealList
import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetFavoriteMealListUseCaseTest {

    @get:Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val defaultDispatcherRule: DefaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var mealRepository: MealRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN invoke GetFavoriteMealListUseCaseTest EXPECT Flow List MealModel`() = runTest {
        coEvery { mealRepository.getFavoriteMealList() } returns
                flow { emit(buildMealList(5)) }
        val result = mealRepository.getFavoriteMealList()
        assertThat(result.toList()[0].size, `is`(5) )
    }
}