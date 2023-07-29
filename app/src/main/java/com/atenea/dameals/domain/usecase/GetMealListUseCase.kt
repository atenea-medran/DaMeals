package com.atenea.dameals.domain.usecase

import com.atenea.dameals.data.MealRepository

class GetMealListUseCase(
    private val mealRepository: MealRepository
) {
    suspend fun invoke() = mealRepository.getMealList()
}