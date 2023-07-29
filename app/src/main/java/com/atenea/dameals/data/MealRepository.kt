package com.atenea.dameals.data

import com.atenea.dameals.domain.model.MealModel

interface MealRepository {
    suspend fun getMealList(): List<MealModel>
}