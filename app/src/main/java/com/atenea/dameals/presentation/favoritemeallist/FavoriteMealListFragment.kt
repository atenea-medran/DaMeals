package com.atenea.dameals.presentation.favoritemeallist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.findNavController
import com.atenea.dameals.databinding.FragmentFavoriteMealListBinding
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.DaMealsTheme

class FavoriteMealListFragment : Fragment() {

    private var _binding: FragmentFavoriteMealListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMealListBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DaMealsTheme {
                    FavoriteMealList {
                        findNavController().navigate(
                        FavoriteMealListFragmentDirections.actionFavoriteMealListFragmentToMealDetailFragment(it))
                    }
                }
            }
        }
        return view
    }
}