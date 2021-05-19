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
import com.example.appcentnasachallenge.service.PhotoDatabase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuriosityViewModel(application: Application) : BaseViewModel(application), AdapterView.OnItemSelectedListener {

    private val nasaApiService = NasaAPIService()

    var rovers = MutableLiveData<APIRoverModel>()
    var rovers_2 = listOf<Photos>()
    var mockRovers = arrayListOf<Photos>()

    lateinit var curiositySpinner: Spinner

    private fun showPhotos(roverList: APIRoverModel) {

        rovers.value = roverList.copy()


    }

    fun createSpinner(context: Context, spinner: Spinner) {
        ArrayAdapter.createFromResource(
            context,
            R.array.choices,
            R.layout.support_simple_spinner_dropdown_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
            curiositySpinner = spinner
        }
    }

    fun getDatafromAPI() {


        var call = nasaApiService.getDataCuriousity()

        call.enqueue(object : Callback<APIRoverModel> {
            override fun onResponse(call: Call<APIRoverModel>, response: Response<APIRoverModel>) {
                var roverModels = response.body()
                //storeInSQLite(roverModels!!.photos)
                rovers_2 = roverModels!!.photos.clone() as List<Photos>
                showPhotos(roverModels!!)
            }

            override fun onFailure(call: Call<APIRoverModel>, t: Throwable) {
                t.printStackTrace()
            }


        })


    }


    fun getFragmentModelList(): List<FragmentModel> {
        val fragmentList = ArrayList<FragmentModel>()

        repeat(getTitle().size) { position ->

            val fragmentModel = FragmentModel(
                getTitle()[position],
            )

            fragmentList.add(fragmentModel)
        }

        return fragmentList

    }

    fun getTitle(): List<String> {
        val titles = arrayListOf("Curiosity", "Opportunity", "Spirit")
        return titles

    }

//    private fun storeInSQLite(list: List<Photos>) {
//        launch {
//            val dao = PhotoDatabase(getApplication()).photoDao()
//
//            dao.deleteAllPhotos()
//            dao.insertAll(*list.toTypedArray())
//
//        }
//
//    }
//
//    private fun getDataFromSQLite() {
//        launch {
//            rovers_2 = PhotoDatabase(getApplication()).photoDao().getAllPhotos()
//
//
//        }
//    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        val selectedOption = parent!!.getItemAtPosition(position).toString()

        if (selectedOption == parent!!.getItemAtPosition(0).toString()) {
            return
        }


        if(mockRovers != null) {
            mockRovers.clear()
        }

        //getDataFromSQLite()

        for (i in 0..(rovers_2.size)-1) {
            if (rovers_2[i].camera.name == selectedOption) {
                mockRovers.add(rovers_2[i])
            }
        }


        rovers.value!!.photos.clear().also {
            for (i in 0..(mockRovers.size)-1){
                rovers.value!!.photos.add(mockRovers[i])

            }
        }

        showPhotos(rovers.value!!)

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        return
    }


}