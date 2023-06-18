package com.photoweather.app.util.di

import com.photoweather.app.features.addPost.viewmodels.AddPostViewModel
import com.photoweather.app.features.home.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {
    viewModel{ AddPostViewModel(get(),get(),get(),get()) }
    viewModel{ HomeViewModel(get()) }
}