package com.example.appcentnasachallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.adapter.CategoryRoverAdapter
import com.example.appcentnasachallenge.viewmodel.CuriosityViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var curiosityViewModel : CuriosityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        curiosityViewModel = ViewModelProvider(this).get(CuriosityViewModel::class.java)

        init()
    }

    private fun init() {
        vpRoverCategory.adapter = CategoryRoverAdapter(this,curiosityViewModel.getFragmentModelList())
        val titles = curiosityViewModel.getTitle()
        TabLayoutMediator(
            tabLayoutRoverategory,
            vpRoverCategory
        ) { tab, position -> tab.text = titles[position] }.attach()
    }
}