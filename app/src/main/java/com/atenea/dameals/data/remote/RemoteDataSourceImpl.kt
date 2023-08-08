package com.atenea.dameals.data.remote

import com.atenea.dameals.data.remote.dto.MealDto

class RemoteDataSourceImpl(
    private val mealApi: MealApi
) : RemoteDataSource {
    override suspend fun getMealList(): List<MealDto>? =
        mealApi.getMealList().meals?.sortedBy {
            it.strMeal
        }

    override suspend fun getMealDetail(mealId: String): MealDto? =
        mealApi.getMealDetail(mealId)?.meals?.get(0)
}