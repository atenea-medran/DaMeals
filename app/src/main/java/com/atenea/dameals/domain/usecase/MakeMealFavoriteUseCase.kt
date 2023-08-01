package com.atenea.dameals.domain.usecase

import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.domain.model.MealModel

class MakeMealFavoriteUseCase(
    private val mealRepository: MealRepository
) {
    suspend fun invoke(meal: MealModel) = mealRepository.makeMealFavorite(meal)
}