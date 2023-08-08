package com.atenea.dameals.domain.usecase

import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.data.local.model.MealLocal
import com.atenea.dameals.domain.model.MealModel

class RemoveMealFromFavoriteUseCase(
    private val mealRepository: MealRepository
) {
    suspend fun invoke(meal: MealModel) = mealRepository.removeMealFromFavorites(meal)
}