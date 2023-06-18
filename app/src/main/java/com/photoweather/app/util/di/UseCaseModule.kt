package com.photoweather.app.util.di

import com.photoweather.domain.addPost.local.usecases.AddPostUseCase
import com.photoweather.domain.addPost.local.usecases.GetCitiesUseCase
import com.photoweather.domain.addPost.remote.usecases.GetCityLocationUseCase
import com.photoweather.domain.addPost.remote.usecases.GetWeatherUseCase
import com.photoweather.domain.home.local.usecases.GetPostsUseCase
import org.koin.dsl.module

val UseCaseModule= module {
    single<GetCityLocationUseCase> { GetCityLocationUseCase(get())  }
    single<GetWeatherUseCase> { GetWeatherUseCase(get())  }
    single<GetCitiesUseCase> { GetCitiesUseCase(get())  }
    single<AddPostUseCase> { AddPostUseCase(get())  }
    single<GetPostsUseCase> { GetPostsUseCase(get())  }
}