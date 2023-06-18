package com.photoweather.domain.addPost.local.repositories

import com.photoweather.domain.addPost.local.models.City
import com.photoweather.domain.common.entites.PostsEntity

interface AddPostLocalRepo {

    suspend fun getCities(): ArrayList<City>
    suspend fun add(post: PostsEntity): Boolean
}