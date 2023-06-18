package com.photoweather.domain.home.local.repositories

import androidx.paging.PagingData
import com.photoweather.domain.common.entites.PostsEntity
import kotlinx.coroutines.flow.Flow

interface HomeLocalRepo {

    suspend fun getPosts(): Flow<PagingData<PostsEntity>>
}