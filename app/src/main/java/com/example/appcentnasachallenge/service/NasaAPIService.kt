package com.example.appcentnasachallenge.service

import com.example.appcentnasachallenge.model.APIRoverModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaAPIService {

    private val BASE_URL = "https://api.nasa.gov/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
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