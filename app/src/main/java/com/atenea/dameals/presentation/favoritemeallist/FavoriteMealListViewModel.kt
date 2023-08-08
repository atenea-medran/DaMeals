package com.atenea.dameals.presentation.favoritemeallist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atenea.dameals.domain.model.MealModel
import com.atenea.dameals.domain.usecase.GetFavoriteMealListUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteMealListViewModel(
    private val getFavoriteMealListUseCase: GetFavoriteMealListUseCase,
    private val removeMealFromFavoriteUseCase: RemoveMealFromFavoriteUseCase
) : ViewModel() {

    private val _favoriteMealListFlow =
        MutableStateFlow<FavoriteMealListState>(FavoriteMealListState.Idle)
    val favoriteMealListFlow: StateFlow<FavoriteMealListState> = _favoriteMealListFlow

//    init {
//        getData()
//    }

    fun getData() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    getFavoriteMealListUseCase.invoke().collect { result ->
                        _favoriteMealListFlow.value = FavoriteMealListState.FavoriteMealList(result)
                    }
                }
            } catch (_: Throwable) {
            }
        }
    }

    fun removeMealFromFavoriteList(meal: MealModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                meal.favorite = false
                removeMealFromFavoriteUseCase.invoke(meal)
            }
        }
    }


}