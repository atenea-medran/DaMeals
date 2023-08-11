package com.atenea.dameals.presentation.mealdetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.atenea.dameals.R
import com.atenea.dameals.components.HeartComposeComponent
import com.atenea.dameals.databinding.FragmentMealDetailBinding
import com.atenea.dameals.presentation.favoritemeallist.ui.theme.DaMealsTheme
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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        mealDetailViewModel.meal.observe(viewLifecycleOwner) { meal ->
            with(binding) {
                tvMealName.text = meal?.strMeal
                ivMealImage.load(meal?.strMealThumb)
                ivFavorite.apply {
                    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                    setContent {
                        DaMealsTheme {
                            if (meal != null) {
                                HeartComposeComponent(favorite = meal.favorite) {
                                    if (!meal.favorite) mealDetailViewModel.makeMealFavorite(meal)
                                    else mealDetailViewModel.removeMealFromFavoriteList(meal)
                                    mealDetailViewModel.getData(args.mealId)
                                }
                            }
                        }
                    }
                }
                with(meal) {
                    tvIngredients.text = getString(
                        R.string.ingredients,
                        this?.strIngredient1?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient2?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient3?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient4?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient5?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient6?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient7?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient8?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient9?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient10?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient11?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient12?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient13?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient14?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient15?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient16?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient17?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient18?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient19?.replaceFirstChar { it.uppercase() },
                        this?.strIngredient20?.replaceFirstChar { it.uppercase() },
                    ).trim()

                    tvQuantity.text = getString(
                        R.string.measures,
                        this?.strMeasure1 ?: "",
                        this?.strMeasure2 ?: "",
                        this?.strMeasure3 ?: "",
                        this?.strMeasure4 ?: "",
                        this?.strMeasure5 ?: "",
                        this?.strMeasure6 ?: "",
                        this?.strMeasure7 ?: "",
                        this?.strMeasure8 ?: "",
                        this?.strMeasure9 ?: "",
                        this?.strMeasure10 ?: "",
                        this?.strMeasure11 ?: "",
                        this?.strMeasure12 ?: "",
                        this?.strMeasure13 ?: "",
                        this?.strMeasure14 ?: "",
                        this?.strMeasure15 ?: "",
                        this?.strMeasure16 ?: "",
                        this?.strMeasure17 ?: "",
                        this?.strMeasure18 ?: "",
                        this?.strMeasure19 ?: "",
                        this?.strMeasure20 ?: ""
                    ).trim()
                    tvInstructions.text = meal?.strInstructions
                }
            }
        }
        mealDetailViewModel.getData(args.mealId)
    }

}