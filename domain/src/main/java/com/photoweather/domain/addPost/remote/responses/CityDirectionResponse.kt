package com.photoweather.domain.addPost.remote.responses

import com.google.gson.annotations.SerializedName

data class CityDirectionResponse(
    @SerializedName("name"        ) var name       : String?     = null,
    @SerializedName("lat"         ) var lat        : Double?     = null,
    @SerializedName("lon"         ) var lon        : Double?     = null,
)
