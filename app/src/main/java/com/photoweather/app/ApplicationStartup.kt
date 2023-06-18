package com.photoweather.app

import android.app.Application
import com.photoweather.app.util.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level


class ApplicationStartup: Application()  {

    override fun onCreate() {
        super.onCreate()
        startKoin(this)
    }
    private fun startKoin(app: Application) {
        org.koin.core.context.startKoin {
            androidLogger(Level.ERROR)
            androidContext(app)
            modules(
                ViewModelModule,
                RepositoryModule,
                UseCaseModule,
                NetworkModule,
                LocalModule

            )
        }
    }
}