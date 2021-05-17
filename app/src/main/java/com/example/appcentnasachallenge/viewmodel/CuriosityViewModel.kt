package com.example.appcentnasachallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appcentnasachallenge.model.FragmentModel
import com.example.appcentnasachallenge.ui.CuriosityFragment

class CuriosityViewModel : ViewModel() {


    fun getFragmentModelList() : List<FragmentModel> {
        val fragmentList = ArrayList<FragmentModel>()

        repeat(getTitle().size) { position ->

            val roverFragment = CuriosityFragment()
            val fragmentModel = FragmentModel(
                getTitle()[position],
                roverFragment

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