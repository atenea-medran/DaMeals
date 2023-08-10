package com.atenea.dameals.presentation.mealdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atenea.dameals.domain.model.MealModel
import com.atenea.dameals.domain.usecase.GetMealDetailUseCase
import com.atenea.dameals.domain.usecase.MakeMealFavoriteUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealDetailViewModel(
    private val getMealDetailUseCase: GetMealDetailUseCase,
    private val makeMealFavoriteUseCase: MakeMealFavoriteUseCase,
    private val removeMealFromFavoriteUseCase: RemoveMealFromFavoriteUseCase
) : ViewModel() {

    private val _meal = MutableLiveData<MealModel?>()
    val meal: MutableLiveData<MealModel?> get() = _meal

    fun getData(mealId: String) {
        getMeal(mealId)
    }

    fun getMeal(mealId: String) {
        try {
            viewModelScope.launch {
                val result = withContext(Dispatchers.IO) {
                    getMealDetailUseCase.invoke(mealId)
                }
                _meal.value = result
            }
        } catch (t: Throwable) {
            Log.d("ERROR", t.toString())
        }
    }

    fun makeMealFavorite(meal: MealModel) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                meal.favorite = true
                makeMealFavoriteUseCase.invoke(meal)
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