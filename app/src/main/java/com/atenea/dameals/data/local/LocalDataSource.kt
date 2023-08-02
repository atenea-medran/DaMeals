package com.atenea.dameals.data.local

import com.atenea.dameals.data.local.model.MealLocal
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getFavoriteMealList(): Flow<List<MealLocal>>
    suspend fun makeMealFavorite(meal: MealLocal)
    suspend fun removeMealFromFavorites(meal: MealLocal)
}