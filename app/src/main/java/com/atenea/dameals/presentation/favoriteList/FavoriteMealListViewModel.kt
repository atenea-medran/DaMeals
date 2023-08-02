package com.atenea.dameals.presentation.favoriteList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atenea.dameals.domain.usecase.GetFavoriteMealListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteMealListViewModel(
    private val getFavoriteMealListUseCase: GetFavoriteMealListUseCase
) : ViewModel() {

    private val _favoriteMealListFlow =
        MutableStateFlow<FavoriteMealListState>(FavoriteMealListState.Idle)
    val favoriteMealListFlow: StateFlow<FavoriteMealListState> = _favoriteMealListFlow

    init {
        getData()
    }

    private fun getData() {
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


}