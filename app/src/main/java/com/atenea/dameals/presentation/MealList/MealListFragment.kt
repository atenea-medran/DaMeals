package com.atenea.dameals.presentation.MealList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    private fun initList(data: List<MealModel>) = binding.rvMealList.run {
        adapter = MealListAdapter(data) { model ->
            // TODO
        }
    }


}