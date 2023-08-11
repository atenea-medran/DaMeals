package com.atenea.dameals.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.buildMealList
import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.data.MealRepositoryImpl
import com.atenea.dameals.domain.model.MealModel
import com.atenea.dameals.mealModelDataBuilder
import com.atenea.dameals.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MakeMealFavoriteUseCaseTest {

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
        mealRepository.makeMealFavorite(mealModelDataBuilder())
    }
}