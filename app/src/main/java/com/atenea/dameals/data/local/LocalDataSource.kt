package com.atenea.dameals.data.local

import com.atenea.dameals.data.local.model.MealLocal

interface LocalDataSource {
    suspend fun getMealList(): List<MealLocal>
    suspend fun makeMealFavorite(meal: MealLocal)
    suspend fun removeMealFromFavorites(meal: MealLocal)
}