package com.freewillsolutions.True.selfdcare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen

class CovidNegativeScreen :BaseScreen(){

    lateinit var rootView : View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.screen_covid_negative,container,false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun subscribeUi(){}
}