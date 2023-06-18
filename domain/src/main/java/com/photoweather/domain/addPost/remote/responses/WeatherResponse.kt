package com.photoweather.domain.addPost.remote.responses

import com.google.gson.annotations.SerializedName
import com.photoweather.domain.addPost.remote.models.SnapShotDetails

data class WeatherResponse(
    @SerializedName("cod"     ) var cod     : String?         = null,
    @SerializedName("message" ) var message : Int?            = null,
    @SerializedName("cnt"     ) var cnt     : Int?            = null,
    @SerializedName("list"    ) var list    : ArrayList<SnapShotDetails> = arrayListOf(),
)
