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

class CuriosityViewModel : ViewModel() {

    private val nasaApiService = NasaAPIService()
    private val disposable = CompositeDisposable()

    val rovers = MutableLiveData<List<APIRoverModel>>()



    private fun showPhotos(roverList : List<APIRoverModel>) {
        rovers.value = roverList

    }

    fun getDatafromAPI() {



        disposable.add(
            nasaApiService.getDataCuriousity()
                .subscribeOn(Schedulers.newThread())
                .subscribeWith(object : DisposableSingleObserver<List<APIRoverModel>>() {
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }



                    override fun onSuccess(t: List<APIRoverModel>) {
                        showPhotos(t)
                    }


                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
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