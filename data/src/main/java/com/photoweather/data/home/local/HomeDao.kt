package com.photoweather.data.home.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.photoweather.domain.common.entites.PostsEntity

@Dao
interface HomeDao {

     @Query("SELECT * FROM PostsEntity")
      fun getAll(): PagingSource<Int, PostsEntity>

}