package com.photoweather.domain.home.local.usecases

import com.photoweather.domain.home.local.repositories.HomeLocalRepo


class GetPostsUseCase(private val homeRepo: HomeLocalRepo) {
    suspend operator fun invoke()=homeRepo.getPosts()
}
