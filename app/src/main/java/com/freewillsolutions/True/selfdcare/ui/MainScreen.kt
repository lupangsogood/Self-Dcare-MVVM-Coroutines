package com.freewillsolutions.True.selfdcare.ui

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen
import com.freewillsolutions.True.selfdcare.utility.extensions.fadeIn
import com.freewillsolutions.True.selfdcare.utility.extensions.showAlertDialog
import com.jozzee.android.core.fragment.addFragment
import com.jozzee.android.core.fragment.replaceChildFragment
import com.jozzee.android.core.view.showToast
import kotlinx.android.synthetic.main.main_screen.*

class MainScreen : BaseScreen(){

    lateinit var rootView : View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.main_screen,container,false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi(){
        seeMapTxt.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        setupAction()
    }

    private fun setupAction(){
        appCompatImageView3.setOnClickListener {
            addFragment(CovidNegativeScreen(), fadeIn())
        }

        appCompatImageView4.setOnClickListener {
            addFragment(CovidQuarantineScreen(), fadeIn())
        }

        appCompatImageView5.setOnClickListener {
            showToast("ยังไม่เปิดให้บริการ")
        }
    }
}