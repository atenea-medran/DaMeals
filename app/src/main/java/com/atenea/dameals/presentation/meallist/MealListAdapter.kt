package com.atenea.dameals.presentation.meallist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atenea.dameals.databinding.ItemMealBinding
import com.atenea.dameals.domain.model.MealModel

class MealListAdapter(
    private val list: List<MealModel>,
    private val itemOnClick: (MealModel) -> Unit,
    private val starOnClick: (MealModel) -> Unit
) : RecyclerView.Adapter<MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder =
        MealViewHolder(ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) =
        holder.bind(list[position], itemOnClick, starOnClick)
}