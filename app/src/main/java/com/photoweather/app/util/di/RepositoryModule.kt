package com.photoweather.app.util.di

import androidx.paging.ExperimentalPagingApi
import com.photoweather.data.addPost.local.repoImpl.AddPostLocalRepoImpl
import com.photoweather.data.addPost.remote.repoImpl.AddPostRemoteRepoImpl
import com.photoweather.data.home.local.repoImp.HomeLocalRepoImpl
import com.photoweather.domain.addPost.local.repositories.AddPostLocalRepo
import com.photoweather.domain.addPost.remote.repositories.AddPostRemoteRepo
import com.photoweather.domain.home.local.repositories.HomeLocalRepo
import org.koin.dsl.module

@OptIn(ExperimentalPagingApi::class)
val RepositoryModule=module{

    single<AddPostRemoteRepo> { AddPostRemoteRepoImpl(get()) }
    single<AddPostLocalRepo> { AddPostLocalRepoImpl(get(),get()) }
    single<HomeLocalRepo> { HomeLocalRepoImpl(get(),get()) }
}