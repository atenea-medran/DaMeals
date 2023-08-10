package com.atenea.dameals.data.local

import com.atenea.dameals.data.local.model.MealLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDataSourceImpl(
    private val mealDao: MealDao
) : LocalDataSource {
    override suspend fun insertMealList(mealList: List<MealLocal>) =
        mealDao.insertAll(mealList)

    override suspend fun getMealList(): List<MealLocal> =
        mealDao.getAll()

    override suspend fun getMealDetail(idMeal: String): MealLocal =
        mealDao.getMealDetail(idMeal)


    override suspend fun getFavoriteMealList(): Flow<List<MealLocal>> =
        flow { emit(mealDao.getAll()) }

    override suspend fun makeMealFavorite(meal: MealLocal) =
        mealDao.insertMeal(meal)

    override suspend fun removeMealFromFavorites(meal: MealLocal) {
        meal.favorite = false
        mealDao.insertMeal(meal)
    }

}