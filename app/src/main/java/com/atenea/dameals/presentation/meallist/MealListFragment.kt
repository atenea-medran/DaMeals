package com.atenea.dameals.presentation.meallist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.atenea.dameals.databinding.FragmentMealListBinding
import com.atenea.dameals.domain.model.MealModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MealListFragment : Fragment() {

    private val mealListViewModel: MealListViewModel by viewModel()
    private lateinit var binding: FragmentMealListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMealListBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btReload.setOnClickListener {
            mealListViewModel.getData()
            hideErrorMessageAndReloadButton()
        }

        mealListViewModel.mealList.observe(viewLifecycleOwner) { mealList ->
            if (mealList != null) {
                initList(mealList)
            }
        }

        mealListViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                showErrorMessageAndReloadButton(it)
            } ?: run {
                hideErrorMessageAndReloadButton()
            }
        }
        mealListViewModel.getData()
    }

    private fun showErrorMessageAndReloadButton(message: String) {
        binding.tvErrorText.apply {
            visibility = View.VISIBLE
            text = message
        }
        binding.btReload.visibility = View.VISIBLE
    }

    private fun hideErrorMessageAndReloadButton() {
        binding.tvErrorText.visibility = View.GONE
        binding.btReload.visibility = View.GONE
    }

    private fun initList(data: List<MealModel>) = binding.rvMealList.run {
        this.layoutManager = GridLayoutManager(context, 2)

        adapter = MealListAdapter(
            data,
            { meal ->
                findNavController().navigate(
                    MealListFragmentDirections.actionMealListFragmentToMealDetailFragment(
                        meal.idMeal
                    )
                )
            },
            { meal ->
                if (!meal.favorite) mealListViewModel.makeMealFavorite(meal)
                else mealListViewModel.removeMealFromFavoriteList(meal)
            }
        )
    }
}