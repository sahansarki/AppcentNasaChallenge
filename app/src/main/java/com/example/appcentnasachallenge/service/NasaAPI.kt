package com.example.appcentnasachallenge.service

import com.example.appcentnasachallenge.model.APIRoverModel
import retrofit2.Call
import retrofit2.http.GET

interface NasaAPI {


    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO")
    fun getCuriosityRover() : Call<APIRoverModel>

    @GET("mars-photos/api/v1/rovers/opportunity/photos?sol=100&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO")
    fun getopportunityRover() : Call<APIRoverModel>

    @GET("mars-photos/api/v1/rovers/spirit/photos?sol=10&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO")
    fun getSpiritRover() : Call<APIRoverModel>

}