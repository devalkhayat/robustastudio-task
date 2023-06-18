package com.photoweather.domain.common.entites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostsEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    var placeName:String,
    var degree:String,
    var city:String,
    var description:String,
    var largeImageLocation:String,
    var smallImageLocation:String,
):java.io.Serializable
