package com.atenea.dameals.presentation.favoriteList

import com.atenea.dameals.domain.model.MealModel

sealed class FavoriteMealListState  {
    object Idle: FavoriteMealListState()
    object Loading: FavoriteMealListState()
    data class FavoriteMealList(val favoriteMealList: List<MealModel>) : FavoriteMealListState()
}