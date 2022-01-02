package com.example.foodmarketonline.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foodmarketonline.model.reponse.transaction.Data
import com.example.foodmarketonline.ui.order.inprogress.InprogressFragment
import com.example.foodmarketonline.ui.order.pastorders.PastordersFragment

@Suppress("DEPRECATION")
class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var inprogressList:ArrayList<Data>? = ArrayList()
    private var pastordersList:ArrayList<Data>? = ArrayList()


    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
       return when(position){
           0 -> "In Progress"
           1 -> "Past Orders"
           else -> ""
       }
    }

    override fun getItem(position: Int): Fragment {
        val fragment : Fragment
        when(position){
            0 -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastordersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastordersList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    fun setData(inprogressListParms: ArrayList<Data>?, postordersListParms: ArrayList<Data>?){
        inprogressList = inprogressListParms
        pastordersList = postordersListParms
    }

}