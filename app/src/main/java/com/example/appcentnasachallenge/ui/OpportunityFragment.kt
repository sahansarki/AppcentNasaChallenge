package com.example.appcentnasachallenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.adapter.RoversPhotoAdapter
import com.example.appcentnasachallenge.viewmodel.CuriosityViewModel
import com.example.appcentnasachallenge.viewmodel.OpportunityViewModel
import kotlinx.android.synthetic.main.fragment_curiosity.*
import kotlinx.android.synthetic.main.fragment_curiosity.recycRoversList
import kotlinx.android.synthetic.main.fragment_opportunity.*

class OpportunityFragment : Fragment(R.layout.fragment_opportunity) {

    private lateinit var viewModelOpportunity : OpportunityViewModel


    private val roverAdapter = RoversPhotoAdapter(null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelOpportunity = ViewModelProvider(this).get(OpportunityViewModel::class.java)
        viewModelOpportunity.createSpinner(this.requireContext(),opportunity_spinner)
        viewModelOpportunity.getDatafromAPI_Opportunity()



        recycRoversList.layoutManager = LinearLayoutManager(context)
        recycRoversList.adapter = roverAdapter

        observeLiveData()




    }



    private fun observeLiveData() {
        viewModelOpportunity.opportunityRovers.observe(viewLifecycleOwner, { rovers ->
            rovers?.let {
                recycRoversList.visibility = View.VISIBLE
                //roverAdapter.changed()
                roverAdapter.roverPhotos = rovers
                roverAdapter.updateRoverPhotos(rovers)

            }

        })


    }
}