package com.atenea.dameals.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.mealDtoDataBuilder
import com.atenea.dameals.mealLocalDataBuilder
import com.atenea.dameals.data.local.LocalDataSource
import com.atenea.dameals.data.mappers.toMealModel
import com.atenea.dameals.data.remote.RemoteDataSource
import com.atenea.dameals.data.remote.dto.MealDto
import com.atenea.dameals.testutil.DefaultDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MealRepositoryImplTest {
    @get:Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val defaultDispatcherRule: DefaultDispatcherRule = DefaultDispatcherRule()

    @MockK(relaxed = true)
    private lateinit var localDataSource: LocalDataSource

    @MockK(relaxed = true)
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `WHEN localData && getMealList EXPECT remote data`() = runTest {
        coEvery { localDataSource.getMealList() } returns getListLocal()
        coEvery { remoteDataSource.getMealList() } returns listOf()

        val repo = MealRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

        val result = repo.getMealList()
        assertThat(result, instanceOf(List::class.java))
        assertThat(result?.size, `is`(2))
    }
    @Test
    fun `WHEN not localData && getMealList EXPECT remote data`() = runTest {
        coEvery { localDataSource.getMealList() } returns listOf()
        coEvery { remoteDataSource.getMealList() } returns getListRemote()

        val repo = MealRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )

        val result = repo.getMealList()
        assertThat(result, instanceOf(List::class.java))
        assertThat(result?.size, `is`(3))
    }


}

fun getListLocal() = listOf(mealLocalDataBuilder(), mealLocalDataBuilder())

fun getListRemote() = listOf(mealDtoDataBuilder(), mealDtoDataBuilder(), mealDtoDataBuilder())