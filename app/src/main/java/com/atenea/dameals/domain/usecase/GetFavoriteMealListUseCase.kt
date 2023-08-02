package com.atenea.dameals.domain.usecase

import com.atenea.dameals.data.MealRepository
import com.atenea.dameals.domain.model.MealModel
import kotlinx.coroutines.flow.Flow

class GetFavoriteMealListUseCase (
    private val mealRepository: MealRepository
) {
    suspend fun invoke(): Flow<List<MealModel>> = mealRepository.getFavoriteMealList()
}