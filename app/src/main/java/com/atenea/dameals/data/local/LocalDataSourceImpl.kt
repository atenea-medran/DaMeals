package com.atenea.dameals.data.local

import com.atenea.dameals.data.local.model.MealLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(
    private val mealDao: MealDao
) : LocalDataSource {
    override suspend fun getFavoriteMealList(): Flow<List<MealLocal>> =
        flow { emit(mealDao.getAll()) }

    override suspend fun makeMealFavorite(meal: MealLocal) =
        mealDao.insertMeal(meal)

    override suspend fun removeMealFromFavorites(meal: MealLocal) =
        mealDao.deleteMeal(meal)

}