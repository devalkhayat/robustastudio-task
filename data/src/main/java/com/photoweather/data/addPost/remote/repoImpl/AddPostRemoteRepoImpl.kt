package com.photoweather.data.addPost.remote.repoImpl

import com.photoweather.data.addPost.remote.EndPoints
import com.photoweather.data.common.RetrofitBuilder
import com.photoweather.domain.addPost.remote.repositories.AddPostRemoteRepo
import com.photoweather.domain.addPost.remote.responses.CityDirectionResponse
import com.photoweather.domain.addPost.remote.responses.WeatherResponse
import com.photoweather.domain.common.FinalResponse


class AddPostRemoteRepoImpl(private val retrofitBuilder: RetrofitBuilder): AddPostRemoteRepo {


    private val endPoints=retrofitBuilder.start()?.create(EndPoints::class.java)

    override suspend fun getCityLocation(cityName: String): FinalResponse<ArrayList<CityDirectionResponse>> {
        try {

            val result =  endPoints?.getCityLocation(cityName)

            result.let {

                when (it?.code()) {
                    //Success

                    200 ->  {
                        return FinalResponse(data =it.body()!!, status = true)
                    }

                    else -> {
                        return FinalResponse(message = result?.message(), status = false)
                    }
                }

            }


        }catch (ex:Exception){

            return FinalResponse( message = ex.message!!, status = false)
        }

    }

    override suspend fun getWeatherInfo(latitude: Double, longitude: Double): FinalResponse<WeatherResponse> {

        try {

            val result =  endPoints?.getWeatherInfo(latitude,longitude)

            result.let {

                when (it?.code()) {
                    //Success

                    200 ->  {
                        return FinalResponse(data =it.body()!!, status = true)
                    }

                    else -> {
                        return FinalResponse(message = result?.message(), status = false)
                    }
                }

            }


        }catch (ex:Exception){

            return FinalResponse( message = ex.message!!, status = false)
        }
    }


}