package com.photoweather.data.addPost.local.repoImpl

import android.annotation.SuppressLint
import android.content.Context
import com.photoweather.data.common.LocalDatabase
import com.photoweather.resources.R
import com.photoweather.domain.addPost.local.models.City
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.domain.addPost.local.repositories.AddPostLocalRepo


class AddPostLocalRepoImpl(val _context:Context,private val  localDatabase: LocalDatabase): AddPostLocalRepo {

    private  val TAG = "AddPostLocalRepoImpl"
    //val appThemeList = res.getStringArray(R.array.themeList)
    @SuppressLint("ResourceType")
    override suspend fun getCities(): ArrayList<City> {
        val cities=ArrayList<City>()
        val data=_context.resources.getStringArray(R.array.cities)
        data.forEach {
            cities.add(City(it))
        }
        return cities
    }

    override suspend fun add(post: PostsEntity): Boolean {

        if(localDatabase.addPostDao().insertPost(post)>0)
        {
            //return
        }
        return true
    }


}