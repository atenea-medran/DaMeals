package com.atenea.dameals.di

import android.content.Context
import androidx.room.Room
import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.data.MealRepositoryImpl
import com.atenea.dameals.data.local.LocalDataSource
import com.atenea.dameals.data.local.LocalDataSourceImpl
import com.atenea.dameals.data.local.MealDao
import com.atenea.dameals.data.local.MealDatabase
import com.atenea.dameals.data.remote.MealApi
import com.atenea.dameals.data.remote.RemoteDataSource
import com.atenea.dameals.data.remote.RemoteDataSourceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<Moshi> {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single<MealRepository> { MealRepositoryImpl(get(), get()) }

    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }

    single<LocalDataSource> { LocalDataSourceImpl(get()) }

    single<MealApi> {
        getMealApi(get())
    }

    single {
        getDatabase(get())
    }

    single {
        providesMealDao(get())
    }
}

private fun getMealApi(retrofit: Retrofit) = retrofit.create(MealApi::class.java)

private fun getDatabase(context: Context) : MealDatabase =
    Room.databaseBuilder(
        context,
        MealDatabase::class.java, "meal-db"
    ).build()

private fun providesMealDao(db: MealDatabase) : MealDao =
    db.mealDao()
