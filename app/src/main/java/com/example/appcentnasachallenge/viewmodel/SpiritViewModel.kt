package com.example.appcentnasachallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.service.NasaAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpiritViewModel : ViewModel() {

    private val nasaApiService = NasaAPIService()

    val spiritRovers = MutableLiveData<APIRoverModel>()



    private fun showPhotos(roverList : APIRoverModel) {

        spiritRovers.value = roverList

    }

    fun getDatafromAPI_Spirit() {


        var call = nasaApiService.getDataSpirit()

        call.enqueue(object: Callback<APIRoverModel> {
            override fun onResponse(call: Call<APIRoverModel>, response: Response<APIRoverModel>) {
                var roverModels = response.body()
                showPhotos(roverModels!!)
            }

            override fun onFailure(call: Call<APIRoverModel>, t: Throwable) {
                t.printStackTrace()
            }


        })

    }
}