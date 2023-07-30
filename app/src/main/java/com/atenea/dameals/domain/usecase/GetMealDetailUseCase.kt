package com.atenea.dameals.domain.usecase

import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.domain.model.MealModel

class GetMealDetailUseCase(
    private val mealRepository: MealRepository
) {
    suspend fun invoke(mealId: String): MealModel = mealRepository.getMealDetail(mealId)
}