package com.example.appcentnasachallenge.service

import com.example.appcentnasachallenge.model.APIRoverModel
import io.reactivex.Single
import retrofit2.http.GET

interface NasaAPI {

// mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO")
    fun getCuriosityRover() : Single<List<APIRoverModel>>

    @GET("mars-photos/api/v1/rovers/opportunity/photos?sol=1000&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO")
    fun getopportunityRover() : Single<List<APIRoverModel>>

    @GET("mars-photos/api/v1/rovers/spirit/photos?sol=1000&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO")
    fun getSpiritRover() : Single<List<APIRoverModel>>

}