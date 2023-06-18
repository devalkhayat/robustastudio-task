package com.photoweather.app.util.di

import com.photoweather.data.common.RetrofitBuilder
import org.koin.dsl.module

val NetworkModule = module {
    single { RetrofitBuilder() }
}