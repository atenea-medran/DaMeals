package com.atenea.dameals.data.local

import com.atenea.dameals.data.local.model.MealLocal

class LocalDataSourceImpl(
    private val mealDao: MealDao
) : LocalDataSource {
    override suspend fun getMealList(): List<MealLocal> =
        mealDao.getAll()


    override suspend fun makeMealFavorite(meal: MealLocal) =
        mealDao.insertMeal(meal)

    override suspend fun removeMealFromFavorites(meal: MealLocal) =
        mealDao.deleteMeal(meal)

}