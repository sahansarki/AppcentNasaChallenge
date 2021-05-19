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
import com.example.appcentnasachallenge.model.Photos
import com.example.appcentnasachallenge.service.NasaAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpiritViewModel(application : Application) : BaseViewModel(application), AdapterView.OnItemSelectedListener {

    private val nasaApiService = NasaAPIService()

    val spiritRovers = MutableLiveData<APIRoverModel>()
    var rovers_2 = listOf<Photos>()
    var mockRovers = arrayListOf<Photos>()
    lateinit var spiritSpinner: Spinner


    private fun showPhotos(roverList : APIRoverModel) {

        spiritRovers.value = roverList

    }

    fun getDatafromAPI_Spirit() {


        var call = nasaApiService.getDataSpirit()

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
            spiritSpinner = spinner
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


        spiritRovers.value!!.photos.clear().also {
            for (i in 0..(mockRovers.size)-1){
                spiritRovers.value!!.photos.add(mockRovers[i])

            }
        }

        showPhotos(spiritRovers.value!!)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        return
    }
}