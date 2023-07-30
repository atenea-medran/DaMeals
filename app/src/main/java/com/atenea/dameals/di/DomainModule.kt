package com.atenea.dameals.di

import com.atenea.dameals.domain.usecase.GetMealDetailUseCase
import com.atenea.dameals.domain.usecase.GetMealListUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetMealListUseCase(get()) }
    single { GetMealDetailUseCase(get()) }
}