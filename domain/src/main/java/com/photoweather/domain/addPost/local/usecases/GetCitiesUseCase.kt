package com.photoweather.domain.addPost.local.usecases

import com.photoweather.domain.addPost.local.repositories.AddPostLocalRepo


class GetCitiesUseCase(private val addPostRepo: AddPostLocalRepo) {
    suspend operator fun invoke()=addPostRepo.getCities()
}
