package com.photoweather.domain.addPost.remote.usecases

import com.photoweather.domain.addPost.remote.repositories.AddPostRemoteRepo


class GetCityLocationUseCase(private val addPostRepo: AddPostRemoteRepo) {
    suspend operator fun invoke(cityName:String)=addPostRepo.getCityLocation(cityName)
}
