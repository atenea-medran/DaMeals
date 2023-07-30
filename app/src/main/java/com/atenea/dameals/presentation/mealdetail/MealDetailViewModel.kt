package com.atenea.dameals.presentation.mealdetail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atenea.dameals.domain.model.MealModel
import com.atenea.dameals.domain.usecase.GetMealDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MealDetailViewModel(
    private val context: Context,
    private val getMealDetailUseCase: GetMealDetailUseCase
) : ViewModel() {

    private val _meal = MutableLiveData<MealModel>()
    val meal: LiveData<MealModel> get() = _meal

    fun getData(mealId: String) {
        getMeal(mealId)
    }

    private fun getMeal(mealId: String) =
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getMealDetailUseCase.invoke(mealId)
            }
            _meal.value = result
        }
}