package com.photoweather.domain.addPost.remote.models

import com.google.gson.annotations.SerializedName

data class SnapShotDetails (

    @SerializedName("dt"         ) var dt         : Int?               = null,
    @SerializedName("main"       ) var main       : Main?              = Main(),
    @SerializedName("weather"    ) var weather    : ArrayList<Weather> = arrayListOf(),
    @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
    @SerializedName("dt_txt"     ) var dtTxt      : String?            = null

)