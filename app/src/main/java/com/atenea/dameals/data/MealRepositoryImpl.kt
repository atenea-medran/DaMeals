package com.atenea.dameals.data

import com.atenea.dameals.data.mappers.toMealModel
import com.atenea.dameals.data.remote.RemoteDataSource
import com.atenea.dameals.domain.model.MealModel

class MealRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : MealRepository {
    override suspend fun getMealList(): List<MealModel> =
        remoteDataSource.getMealList().map { it.toMealModel() }
}