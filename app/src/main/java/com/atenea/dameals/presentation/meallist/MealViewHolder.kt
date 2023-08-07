package com.atenea.dameals.presentation.meallist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.atenea.dameals.R
import com.atenea.dameals.databinding.ItemMealBinding
import com.atenea.dameals.domain.model.MealModel

class MealViewHolder(
    private val binding: ItemMealBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        meal: MealModel,
        itemOnClick: (MealModel) -> Unit,
        starOnClick: (MealModel) -> Unit
    ) {
        with(binding) {
            tvMealName.text = meal.strMeal
            ivMealImage.load(meal.strMealThumb)
            if (meal.favorite) putFilledStar()
            root.setOnClickListener {
                itemOnClick(meal)
            }
            ivStar.setOnClickListener {
                starOnClick(meal)
                putFilledStar()
            }
        }

    }

    fun putFilledStar() = binding.ivStar.setImageResource(R.drawable.filled_star)
}
