package com.photoweather.app.features.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.photoweather.data.common.LocalDatabase
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.domain.home.local.usecases.GetPostsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(private val getPostsUseCase: GetPostsUseCase
                   ): ViewModel() {

    private  val TAG = "HomeViewModel"
    suspend fun getPosts():Flow<PagingData<PostsEntity>> {
       return getPostsUseCase()!!.cachedIn(viewModelScope)
    }
}