package com.atenea.dameals.di

import com.atenea.dameals.presentation.favoritemeallist.FavoriteMealListViewModel
import com.atenea.dameals.presentation.mealdetail.MealDetailViewModel
import com.atenea.dameals.presentation.meallist.MealListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MealListViewModel(get(), get(), get()) }
    viewModelOf(::MealListViewModel)

    viewModel { MealDetailViewModel(get(), get(), get()) }
    viewModelOf(::MealDetailViewModel)

    viewModel { FavoriteMealListViewModel(get(), get()) }

}
