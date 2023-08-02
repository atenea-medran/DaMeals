package com.atenea.dameals.data.local

import com.atenea.dameals.data.local.model.MealLocal

interface LocalDataSource {
    suspend fun getFavoriteMealList(): List<MealLocal>
    suspend fun makeMealFavorite(meal: MealLocal)
    suspend fun removeMealFromFavorites(meal: MealLocal)
}