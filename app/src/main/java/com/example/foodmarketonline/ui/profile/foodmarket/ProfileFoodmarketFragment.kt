package com.example.foodmarketonline.ui.profile.foodmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarketonline.R
import com.example.foodmarketonline.model.dummy.ProfileMenuModel
import com.example.foodmarketonline.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_foodmarket.*

class ProfileFoodmarketFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback  {

    private var menuArrayList : ArrayList<ProfileMenuModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_foodmarket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        var adapter = ProfileMenuAdapter(menuArrayList, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    fun initDataDummy(){
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Rate Apps"))
        menuArrayList.add(ProfileMenuModel("Help Center"))
        menuArrayList.add(ProfileMenuModel("Privacy & Policy"))
        menuArrayList.add(ProfileMenuModel("Term & conditions"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, "test", Toast.LENGTH_LONG).show()
    }

}