package com.photoweather.app.util.eventListners

import com.photoweather.domain.common.entites.PostsEntity

interface RecycleViewEventListener:java.io.Serializable {
        fun onClick(name:String){}
        fun onClick(post:PostsEntity){}
}