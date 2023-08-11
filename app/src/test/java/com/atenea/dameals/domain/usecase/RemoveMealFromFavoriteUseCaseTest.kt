package com.atenea.dameals.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.mealModelDataBuilder
import com.atenea.dameals.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RemoveMealFromFavoriteUseCaseTest {

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
    fun `WHEN invoke removeMealFromFavorites EXPECT test passed`() = runTest {
        mealRepository.removeMealFromFavorites(mealModelDataBuilder())
    }
}