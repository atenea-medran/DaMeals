package com.atenea.dameals.di

import com.atenea.dameals.presentation.MealList.MealListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MealListViewModel(get(), get()) }
    viewModelOf(::MealListViewModel)

}
