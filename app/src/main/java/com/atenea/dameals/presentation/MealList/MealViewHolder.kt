package com.atenea.dameals.presentation.MealList

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.atenea.dameals.databinding.ItemMealBinding
import com.atenea.dameals.domain.model.MealModel

class MealViewHolder(
    private val binding: ItemMealBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MealModel, onClick: (MealModel) -> Unit) {
        binding.tvMealName.text = item.strMeal
        binding.ivMealImage.load(item.strMealThumb)
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}
