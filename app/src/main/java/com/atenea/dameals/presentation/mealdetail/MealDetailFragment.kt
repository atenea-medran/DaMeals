package com.atenea.dameals.presentation.mealdetail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings.PluginState
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.MenuHost
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.atenea.dameals.R
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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mealDetailViewModel.meal.observe(viewLifecycleOwner) { meal ->
            with(binding) {
                tvMealName.text = meal.strMeal
                ivMealImage.load(meal.strMealThumb)
                with(meal) {
                    tvIngredients.text = getString(
                        R.string.ingredients,
                        strIngredient1?.replaceFirstChar { it.uppercase() },
                        strIngredient2?.replaceFirstChar { it.uppercase() },
                        strIngredient3?.replaceFirstChar { it.uppercase() },
                        strIngredient4?.replaceFirstChar { it.uppercase() },
                        strIngredient5?.replaceFirstChar { it.uppercase() },
                        strIngredient6?.replaceFirstChar { it.uppercase() },
                        strIngredient7?.replaceFirstChar { it.uppercase() },
                        strIngredient8?.replaceFirstChar { it.uppercase() },
                        strIngredient9?.replaceFirstChar { it.uppercase() },
                        strIngredient10?.replaceFirstChar { it.uppercase() },
                        strIngredient11?.replaceFirstChar { it.uppercase() },
                        strIngredient12?.replaceFirstChar { it.uppercase() },
                        strIngredient13?.replaceFirstChar { it.uppercase() },
                        strIngredient14?.replaceFirstChar { it.uppercase() },
                        strIngredient15?.replaceFirstChar { it.uppercase() },
                        strIngredient16?.replaceFirstChar { it.uppercase() },
                        strIngredient17?.replaceFirstChar { it.uppercase() },
                        strIngredient18?.replaceFirstChar { it.uppercase() },
                        strIngredient19?.replaceFirstChar { it.uppercase() },
                        strIngredient20?.replaceFirstChar { it.uppercase() },
                    ).trim()

                    tvQuantity.text = getString(
                        R.string.measure,
                        strMeasure1,
                        strMeasure2,
                        strMeasure3,
                        strMeasure4,
                        strMeasure5,
                        strMeasure6,
                        strMeasure7,
                        strMeasure8,
                        strMeasure9,
                        strMeasure10,
                        strMeasure11,
                        strMeasure12,
                        strMeasure13,
                        strMeasure14,
                        strMeasure15,
                        strMeasure16,
                        strMeasure17,
                        strMeasure18,
                        strMeasure19,
                        strMeasure20
                    ).trim()
                    tvInstructions.text = meal.strInstructions
                }

//                wvYoutube.webChromeClient = WebChromeClient()
//                wvYoutube.
//                wvYoutube.setOnClickListener {
//                wvYoutube.loadUrl(meal.strYoutube!!)
            }
        }
        mealDetailViewModel.getData(args.mealId)
    }

}