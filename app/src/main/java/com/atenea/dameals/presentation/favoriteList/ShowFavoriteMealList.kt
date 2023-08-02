package com.atenea.dameals.presentation.favoriteList

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.atenea.dameals.domain.model.MealModel

@Composable
fun ShowFavoriteMealList(
    meal: MealModel
) {
    
    Card(
        modifier = Modifier.padding(20.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = meal.strMeal ?: "")
        
    }
}