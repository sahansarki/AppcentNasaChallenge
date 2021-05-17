package com.example.appcentnasachallenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.viewmodel.CuriosityViewModel


class CuriosityFragment : Fragment(R.layout.fragment_curiosity) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstViewModel = CuriosityViewModel()

        //recycRoversList.adapter = RoversPhotoAdapter()

    }
}