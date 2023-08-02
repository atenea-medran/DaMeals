package com.atenea.dameals.presentation.meallist

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atenea.dameals.domain.model.MealModel
import com.atenea.dameals.domain.usecase.GetFavoriteMealListUseCase
import com.atenea.dameals.domain.usecase.GetMealListUseCase
import com.atenea.dameals.domain.usecase.MakeMealFavoriteUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealListViewModel(
    private val context: Context,
    private val getMealListUseCase: GetMealListUseCase,
    private val makeMealFavoriteUseCase: MakeMealFavoriteUseCase,
    private val removeMealFromFavoriteUseCase: RemoveMealFromFavoriteUseCase,
    private val getFavoriteMealListUseCase: GetFavoriteMealListUseCase
) : ViewModel() {

    private val _mealList = MutableLiveData<List<MealModel>>()
    val mealList: LiveData<List<MealModel>> get() = _mealList

    fun getData() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    getMealListUseCase.invoke()
                }
                _mealList.value = result
            } catch (t: Throwable) {
                Log.d("Error", "Error en getData()")
            }
        }
    }

    fun makeMealFavorite(meal: MealModel) {
        Log.d("Prueba Favorito", "Clica")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favorites = getFavoriteMealListUseCase.invoke()
                Log.d("Prueba favorito", favorites.size.toString())
                makeMealFavoriteUseCase.invoke(meal)
            }
        }

    }

}