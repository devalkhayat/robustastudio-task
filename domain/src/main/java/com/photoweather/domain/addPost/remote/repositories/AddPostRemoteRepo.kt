package com.photoweather.domain.addPost.remote.repositories

import com.photoweather.domain.addPost.remote.responses.CityDirectionResponse
import com.photoweather.domain.addPost.remote.responses.WeatherResponse
import com.photoweather.domain.common.FinalResponse

interface AddPostRemoteRepo {

    suspend fun getCityLocation(cityName:String): FinalResponse<ArrayList<CityDirectionResponse>>
    suspend fun getWeatherInfo(latitude:Double,longitude:Double): FinalResponse<WeatherResponse>
}