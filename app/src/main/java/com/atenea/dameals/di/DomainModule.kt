package com.atenea.dameals.di

import com.atenea.dameals.domain.usecase.GetFavoriteMealListUseCase
import com.atenea.dameals.domain.usecase.GetMealDetailUseCase
import com.atenea.dameals.domain.usecase.GetMealListUseCase
import com.atenea.dameals.domain.usecase.MakeMealFavoriteUseCase
import com.atenea.dameals.domain.usecase.RemoveMealFromFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetMealListUseCase(get()) }
    single { GetMealDetailUseCase(get()) }
    single { MakeMealFavoriteUseCase(get()) }
    single { RemoveMealFromFavoriteUseCase(get()) }
    single { GetFavoriteMealListUseCase(get())}
}