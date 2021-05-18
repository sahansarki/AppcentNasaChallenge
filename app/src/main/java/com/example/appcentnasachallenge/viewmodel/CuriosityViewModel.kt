package com.example.appcentnasachallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcentnasachallenge.model.APIRoverModel
import com.example.appcentnasachallenge.model.FragmentModel
import com.example.appcentnasachallenge.model.Photos
import com.example.appcentnasachallenge.service.NasaAPIService
import com.example.appcentnasachallenge.ui.CuriosityFragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuriosityViewModel : ViewModel() {

    private val nasaApiService = NasaAPIService()



    val rovers = MutableLiveData<APIRoverModel>()



    private fun showPhotos(roverList : APIRoverModel) {

        rovers.value = roverList

    }

    fun getDatafromAPI() {


        var call = nasaApiService.getDataCuriousity()

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


    fun getFragmentModelList() : List<FragmentModel> {
        val fragmentList = ArrayList<FragmentModel>()

        repeat(getTitle().size) { position ->

            val fragmentModel = FragmentModel(
                getTitle()[position],
            )

            fragmentList.add(fragmentModel)
        }

        return fragmentList

    }

    fun getTitle() : List<String> {
        val titles = arrayListOf("Curiosity" , "Opportunity", "Spirit")
        return titles

    }
}