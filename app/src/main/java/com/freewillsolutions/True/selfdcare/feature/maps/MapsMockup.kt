package com.freewillsolutions.True.selfdcare.feature.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen

class MapsMockup :BaseScreen(){
    lateinit var rootView: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.maps_mockup_screen,container,false)
        return rootView
    }

}