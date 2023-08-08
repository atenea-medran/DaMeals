package com.atenea.dameals.data

import com.atenea.dameals.data.local.LocalDataSource
import com.atenea.dameals.data.mappers.toMealLocal
import com.atenea.dameals.data.mappers.toMealModel
import com.atenea.dameals.data.remote.RemoteDataSource
import com.atenea.dameals.domain.model.MealModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MealRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MealRepository {
    override suspend fun getMealList(): List<MealModel>? {
        val localData = localDataSource.getMealList()
        return if (localData.isNotEmpty()) {
            localData.map { meal ->
                meal.toMealModel()
            }
        } else {
            val remoteData = remoteDataSource.getMealList()
            remoteData?.map { it.toMealLocal() }?.let { localDataSource.insertMealList(it) }

            remoteData?.map {
                it.toMealModel()
            }

        }
    }

    override suspend fun getMealDetail(mealId: String): MealModel? =
        remoteDataSource.getMealDetail(mealId)?.toMealModel()

    override suspend fun makeMealFavorite(meal: MealModel) {
        localDataSource.makeMealFavorite(meal.toMealLocal())
    }

    override suspend fun removeMealFromFavorites(meal: MealModel) {
        localDataSource.removeMealFromFavorites(meal.toMealLocal())
    }

    override suspend fun getFavoriteMealList(): Flow<List<MealModel>> {
        return localDataSource.getFavoriteMealList().map { list ->
            list.map { meal ->
                meal.toMealModel() }
                .filter { meal ->
                    meal.favorite
                }
        }
    }
}