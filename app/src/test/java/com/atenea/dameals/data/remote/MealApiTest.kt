package com.atenea.dameals.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.atenea.dameals.testutil.DefaultDispatcherRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MealApiTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = DefaultDispatcherRule()


    private lateinit var api: MealApi

    @Before
    fun setup() {
        api = retrofit.create(MealApi::class.java)
    }

    @Test
    fun `WHEN request meal list EXPECT meal list`() = runTest {
        val result = api.getMealList()

        assertThat(result.meals?.isNotEmpty(), `is`(true))
    }

    @Test
    fun `WHEN request correct idMeal EXPECT meal`() = runTest {
        val result = api.getMealDetail("52772")

        assertThat(result?.meals?.get(0)?.strMeal, `is`("Teriyaki Chicken Casserole"))
    }

    @Test
    fun `WWHEN request with wrong idMeal EXPECT null value`() = runTest {
        val result = api.getMealDetail("Wrong idmeal")

        assertThat(result?.meals, `is`(nullValue()))
    }


    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                                .apply {
                                    level = HttpLoggingInterceptor.Level.BODY
                                }).build()
                )
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .addLast(KotlinJsonAdapterFactory())
                            .build()
                    )
                ).build()
        }
    }
}