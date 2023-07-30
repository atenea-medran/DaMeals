package com.atenea.dameals.presentation.mealdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.atenea.dameals.databinding.FragmentMealDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealDetailFragment : Fragment() {

    private val args: MealDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentMealDetailBinding
    private val mealDetailViewModel: MealDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMealDetailBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealDetailViewModel.meal.observe(viewLifecycleOwner) {meal ->
            binding.tvMealName.text = meal.strMeal
        }
        mealDetailViewModel.getData(args.mealId)
    }

}