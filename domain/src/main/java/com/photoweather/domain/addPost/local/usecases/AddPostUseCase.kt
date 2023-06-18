package com.photoweather.domain.addPost.local.usecases

import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.domain.addPost.local.repositories.AddPostLocalRepo


class AddPostUseCase(private val addPostRepo: AddPostLocalRepo) {
    suspend operator fun invoke(post: PostsEntity)=addPostRepo.add(post)
}
