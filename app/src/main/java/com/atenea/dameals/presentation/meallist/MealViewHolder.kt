package com.atenea.dameals.presentation.meallist

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.atenea.dameals.databinding.ItemMealBinding
import com.atenea.dameals.domain.model.MealModel

class MealViewHolder(
    private val binding: ItemMealBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: MealModel,
        itemOnClick: (MealModel) -> Unit,
        starOnClick: (MealModel) -> Unit
    ) {
        with(binding) {
            tvMealName.text = item.strMeal
            ivMealImage.load(item.strMealThumb)
            root.setOnClickListener {
                itemOnClick(item)
            }
            ivStar.setOnClickListener {
                starOnClick(item)
            }
        }

    }
}
