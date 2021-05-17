package com.example.appcentnasachallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appcentnasachallenge.R
import com.example.appcentnasachallenge.adapter.CategoryRoverAdapter
import com.example.appcentnasachallenge.viewmodel.CuriosityViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val firstViewModel = CuriosityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        init()
    }

    private fun init() {
        vpRoverCategory.adapter = CategoryRoverAdapter(this,firstViewModel.getFragmentModelList())
        val titles = firstViewModel.getTitle()
        TabLayoutMediator(
            tabLayoutRoverategory,
            vpRoverCategory,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = "${titles[position]}"
                }

            }).attach()
    }
}