package com.atenea.dameals.presentation.favoriteList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atenea.dameals.domain.usecase.GetFavoriteMealListUseCase
import com.atenea.dameals.domain.model.MealModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteMealListScreenViewModel(
    private val getFavoriteMealListUseCase: GetFavoriteMealListUseCase
): ViewModel() {

    private val _favoriteMealList = MutableLiveData<List<MealModel>>()
    val favoriteMealList: LiveData<List<MealModel>> get() = _favoriteMealList

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    getFavoriteMealListUseCase.invoke()
                }
                _favoriteMealList.value = result
            } catch (_: Throwable) {
            }
        }
    }


}