package com.example.appcentnasachallenge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appcentnasachallenge.model.FragmentModel
import com.example.appcentnasachallenge.ui.CuriosityFragment
import com.example.appcentnasachallenge.ui.OpportunityFragment
import com.example.appcentnasachallenge.ui.SpiritFragment

class CategoryRoverAdapter(fa : FragmentActivity, private val roversFragmentList : List<FragmentModel>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (roversFragmentList[position].fragmentTitle) {
            "Curiosity" -> CuriosityFragment()
            "Opportunity" -> OpportunityFragment()
            "Spirit" -> SpiritFragment()

            else -> CuriosityFragment()

        }

        /* if(roversFragmentList[position].fragmentTitle == "Curiosity") return CuriosityFragment()
        else if(roversFragmentList[position].fragmentTitle == "Opportunity") return OpportunityFragment()
        else if(roversFragmentList[position].fragmentTitle == "Spirit") return SpiritFragment()*/

        /*else {
            return CuriosityFragment()
        }*/
    }

}