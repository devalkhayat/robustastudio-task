package com.photoweather.data.home.local.repoImp

import android.content.Context
import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.photoweather.data.common.LocalDatabase
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.domain.home.local.repositories.HomeLocalRepo
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class HomeLocalRepoImpl(val _context:Context, private val  localDatabase: LocalDatabase):
    HomeLocalRepo {

    private val TAG = "HomeLocalRepoImpl"
    override suspend fun getPosts(): Flow<PagingData<PostsEntity>> {
        val items = Pager(PagingConfig(pageSize = 5, enablePlaceholders = true, maxSize = 200)) {
            localDatabase.homeDao().getAll()
        }.flow
        return items
    }

}