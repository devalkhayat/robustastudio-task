package com.photoweather.data.addPost.local

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.photoweather.domain.common.entites.PostsEntity

@Dao
interface AddPostDao {

     @Insert
     suspend fun insertPost(post: PostsEntity):Long

     @Query("SELECT (SELECT COUNT(*) FROM PostsEntity) == 0")
     @Throws(SQLiteException::class)
     suspend fun isEmpty(): Boolean



}