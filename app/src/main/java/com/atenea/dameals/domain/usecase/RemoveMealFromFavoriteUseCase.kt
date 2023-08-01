package com.atenea.dameals.domain.usecase

import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.data.local.model.MealLocal

class RemoveMealFromFavoriteUseCase(
    private val mealRepository: MealRepository
) {
    suspend fun invoke(meal: MealLocal) = mealRepository.removeMealFromFavorites(meal)
}