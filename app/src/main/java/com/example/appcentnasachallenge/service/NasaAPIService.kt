package com.example.appcentnasachallenge.service

import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.model.Photos
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NasaAPIService {
    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=iSkEay7Vo8EcNe8Yh2cQaXMPzq0GP0I2vP2Lg7nO
    private val BASE_URL = "https://api.nasa.gov/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NasaAPI::class.java)

    fun getDataCuriousity(): Call<APIRoverModel> {
        return api.getCuriosityRover()
    }

    fun getDataOpportunity(): Call<APIRoverModel>{
        return api.getopportunityRover()
    }

    fun getDataSpirit(): Call<APIRoverModel> {
        return api.getSpiritRover()
    }
}