package com.example.appcentnasachallenge.viewmodel

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.model.FragmentModel
import com.example.appcentnasachallenge.model.Photos
import com.example.appcentnasachallenge.service.NasaAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OpportunityViewModel(application: Application) : BaseViewModel(application) , AdapterView.OnItemSelectedListener{

    private val nasaApiService = NasaAPIService()

    val opportunityRovers = MutableLiveData<APIRoverModel>()
    var rovers_2 = listOf<Photos>()
    var mockRovers = arrayListOf<Photos>()

    lateinit var opportunitySpinner: Spinner

    private fun showPhotos(roverList : APIRoverModel) {

        opportunityRovers.value = roverList

    }


    fun getDatafromAPI_Opportunity() {


        var call = nasaApiService.getDataOpportunity()

        call.enqueue(object: Callback<APIRoverModel> {
            override fun onResponse(call: Call<APIRoverModel>, response: Response<APIRoverModel>) {
                var roverModels = response.body()
                rovers_2 = roverModels!!.photos.clone() as List<Photos>
                showPhotos(roverModels!!)


            }

            override fun onFailure(call: Call<APIRoverModel>, t: Throwable) {
                t.printStackTrace()
            }


        })

    }

    fun createSpinner(context: Context, spinner: Spinner) {
        ArrayAdapter.createFromResource(
            context,
            R.array.choices_opportunity,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
            opportunitySpinner = spinner
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedOption = parent!!.getItemAtPosition(position).toString()

        if (selectedOption == parent!!.getItemAtPosition(0).toString()) {
            return
        }


        if(mockRovers != null) {
            mockRovers.clear()
        }



        for (i in 0..(rovers_2.size)-1) {
            if (rovers_2[i].camera.name == selectedOption) {
                mockRovers.add(rovers_2[i])
            }
        }


        opportunityRovers.value!!.photos.clear().also {
            for (i in 0..(mockRovers.size)-1){
                opportunityRovers.value!!.photos.add(mockRovers[i])

            }
        }

        showPhotos(opportunityRovers.value!!)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        return
    }


}