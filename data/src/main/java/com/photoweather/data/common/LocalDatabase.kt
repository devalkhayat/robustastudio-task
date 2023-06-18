package com.photoweather.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.photoweather.data.addPost.local.AddPostDao
import com.photoweather.data.home.local.HomeDao
import com.photoweather.domain.common.entites.PostsEntity

@Database(entities = [PostsEntity::class], version = 3)
abstract class LocalDatabase:RoomDatabase() {
    abstract fun addPostDao(): AddPostDao
    abstract fun homeDao(): HomeDao
}