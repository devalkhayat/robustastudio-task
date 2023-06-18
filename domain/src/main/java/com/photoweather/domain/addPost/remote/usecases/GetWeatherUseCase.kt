package com.photoweather.domain.addPost.remote.usecases

import com.photoweather.domain.addPost.remote.repositories.AddPostRemoteRepo


class GetWeatherUseCase(private val addPostRepo: AddPostRemoteRepo) {
    suspend operator fun invoke(latitude:Double, longitude :Double)=addPostRepo.getWeatherInfo(latitude, longitude )
}
