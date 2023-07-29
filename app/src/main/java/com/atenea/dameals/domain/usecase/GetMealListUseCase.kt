package com.atenea.dameals.domain.usecase

import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.domain.model.MealModel

class GetMealListUseCase(
    private val mealRepository: MealRepository
) {
    suspend fun invoke(): List<MealModel> = mealRepository.getMealList()
}