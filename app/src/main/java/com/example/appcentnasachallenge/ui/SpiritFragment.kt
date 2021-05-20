package com.example.appcentnasachallenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.adapter.RoversPhotoAdapter
import com.example.appcentnasachallenge.viewmodel.SpiritViewModel
import kotlinx.android.synthetic.main.fragment_curiosity.recycRoversList
import kotlinx.android.synthetic.main.fragment_spirit.*

class SpiritFragment : Fragment(R.layout.fragment_spirit) {

    private lateinit var viewModelSpirit: SpiritViewModel


    private val roverAdapter = RoversPhotoAdapter(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSpirit = ViewModelProvider(this).get(SpiritViewModel::class.java)
        viewModelSpirit.createSpinner(this.requireContext(), spirit_spinner)
        viewModelSpirit.getDatafromAPISpirit()



        recycRoversList.layoutManager = LinearLayoutManager(context)
        recycRoversList.adapter = roverAdapter

        observeLiveData()




    }


    private fun observeLiveData() {
        viewModelSpirit.spiritRovers.observe(viewLifecycleOwner, { rovers ->
            rovers?.let {
                recycRoversList.visibility = View.VISIBLE
                //roverAdapter.changed()
                roverAdapter.roverPhotos = rovers
                roverAdapter.updateRoverPhotos(rovers)

            }

        })


    }


}