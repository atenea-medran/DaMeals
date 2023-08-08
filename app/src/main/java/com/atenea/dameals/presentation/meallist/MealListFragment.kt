package com.atenea.dameals.presentation.meallist

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        mealListViewModel.mealList.observe(viewLifecycleOwner) { mealList ->
            initList(mealList)
        }
        mealListViewModel.getData()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    private fun initList(data: List<MealModel>) = binding.rvMealList.run {
        this.layoutManager = GridLayoutManager(context,2)

        adapter = MealListAdapter(
            data,
            { meal ->
                findNavController().navigate(MealListFragmentDirections.actionMealListFragmentToMealDetailFragment(meal.idMeal))
            },
            { meal ->
                if (!meal.favorite) mealListViewModel.makeMealFavorite(meal)
                else mealListViewModel.removeMealFromFavoriteList(meal)
            }
        )
    }


}