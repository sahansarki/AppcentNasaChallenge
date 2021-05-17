package com.example.appcentnasachallenge.ui



import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.adapter.RoversPhotoAdapter
import com.example.appcentnasachallenge.viewmodel.CuriosityViewModel
import kotlinx.android.synthetic.main.fragment_curiosity.*


class CuriosityFragment : Fragment(R.layout.fragment_curiosity) {

    private lateinit var viewModelCuriosity : CuriosityViewModel
    private val roverAdapter = RoversPhotoAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelCuriosity = ViewModelProviders.of(this).get(CuriosityViewModel::class.java)
        viewModelCuriosity.getDatafromAPI()

        recycRoversList.layoutManager = LinearLayoutManager(context)
        recycRoversList.adapter = roverAdapter

        observeLiveData()




    }

    private fun observeLiveData() {
        viewModelCuriosity.rovers.observe(viewLifecycleOwner) { rovers ->
            rovers?.let {
                recycRoversList.visibility = View.VISIBLE
                //RoversPhotoAdapter.updateRoverList(rovers)
            }

        }


    }
}