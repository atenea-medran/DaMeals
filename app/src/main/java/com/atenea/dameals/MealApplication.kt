package com.atenea.dameals

import android.app.Application
import com.atenea.dameals.di.dataModule
import com.atenea.dameals.di.domainModule
import com.atenea.dameals.di.presentationModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MealApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.INFO else Level.NONE)
            androidContext(this@MealApplication)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}