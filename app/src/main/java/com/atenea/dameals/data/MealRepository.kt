package com.atenea.dameals.data

import com.atenea.dameals.domain.model.MealModel
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun getMealList(): List<MealModel>?
    suspend fun getMealDetail(mealId: String): MealModel?
    suspend fun makeMealFavorite(meal: MealModel)
    suspend fun removeMealFromFavorites(meal: MealModel)
    suspend fun getFavoriteMealList(): Flow<List<MealModel>>
}