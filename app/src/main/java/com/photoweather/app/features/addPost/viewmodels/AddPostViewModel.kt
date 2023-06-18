package com.photoweather.app.features.addPost.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.photoweather.domain.addPost.local.models.City
import com.photoweather.domain.common.entites.PostsEntity
import com.photoweather.domain.addPost.local.usecases.AddPostUseCase
import com.photoweather.domain.addPost.local.usecases.GetCitiesUseCase
import com.photoweather.domain.addPost.remote.responses.CityDirectionResponse
import com.photoweather.domain.addPost.remote.responses.WeatherResponse
import com.photoweather.domain.addPost.remote.usecases.GetCityLocationUseCase
import com.photoweather.domain.addPost.remote.usecases.GetWeatherUseCase
import com.photoweather.domain.common.FinalResponse
import kotlinx.coroutines.launch

class AddPostViewModel(private val getCityLocationUseCase: GetCityLocationUseCase,
                       private val getWeatherUseCase: GetWeatherUseCase,
                       private val getCitiesUseCase: GetCitiesUseCase,
                       private val addPostUseCase: AddPostUseCase): ViewModel() {

    private  val TAG = "AddPostViewModel"
    private val cityLocation= MutableLiveData<FinalResponse<ArrayList<CityDirectionResponse>>>()
    val cityLocationResponseLive: LiveData<FinalResponse<ArrayList<CityDirectionResponse>>>
        get() = cityLocation

    private val weather= MutableLiveData<FinalResponse<WeatherResponse>>()
    val weatherResponseLive: LiveData<FinalResponse<WeatherResponse>>
        get() = weather

    private val cities= MutableLiveData<ArrayList<City>>()
    val citiesResponseLive: LiveData<ArrayList<City>>
        get() = cities

    private val addPost= MutableLiveData<Boolean>()
    val addPostResponseLive: LiveData<Boolean>
        get() = addPost

    fun getCityLocation(cityName:String) {
        viewModelScope.launch {
            cityLocation.postValue(getCityLocationUseCase(cityName)!!)
        }
    }
    fun getWeather(latitude:Double,longitude:Double) {
        viewModelScope.launch {
            weather.postValue(getWeatherUseCase(latitude,longitude)!!)
        }
    }
    fun getCities() {
        viewModelScope.launch {
            cities.postValue(getCitiesUseCase()!!)
        }
    }
    fun save(post: PostsEntity) {
        viewModelScope.launch {
            addPost.postValue(addPostUseCase(post)!!)
        }
    }
}