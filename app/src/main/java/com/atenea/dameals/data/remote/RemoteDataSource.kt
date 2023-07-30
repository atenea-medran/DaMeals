package com.atenea.dameals.data.remote

import com.atenea.dameals.data.remote.dto.MealDto

interface RemoteDataSource {
    suspend fun getMealList(): List<MealDto>
    suspend fun getMealDetail(mealId: String): MealDto
}