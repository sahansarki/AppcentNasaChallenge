package com.example.appcentnasachallenge.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.model.FragmentModel
import com.example.appcentnasachallenge.service.NasaAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpportunityViewModel(application: Application) : BaseViewModel(application){

    private val nasaApiService = NasaAPIService()

    val opportunityRovers = MutableLiveData<APIRoverModel>()



    private fun showPhotos(roverList : APIRoverModel) {

        opportunityRovers.value = roverList

    }

    fun getDatafromAPI_Opportunity() {


        var call = nasaApiService.getDataOpportunity()

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