package com.atenea.dameals.data

import com.atenea.dameals.data.local.model.MealLocal
import com.atenea.dameals.domain.model.MealModel

interface MealRepository {
    suspend fun getMealList(): List<MealModel>
    suspend fun getMealDetail(mealId: String): MealModel
    suspend fun makeMealFavorite(meal: MealModel)
    suspend fun removeMealFromFavorites(meal: MealLocal)
    suspend fun getFavoriteMealList(): List<MealModel>
}