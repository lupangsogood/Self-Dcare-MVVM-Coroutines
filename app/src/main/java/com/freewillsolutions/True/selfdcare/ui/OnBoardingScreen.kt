package com.freewillsolutions.True.selfdcare.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen
import com.freewillsolutions.True.selfdcare.utility.extensions.setStatusBarColor
import com.freewillsolutions.True.selfdcare.utility.extensions.slideRightToLeft
import com.jozzee.android.core.fragment.replaceFragment
import kotlinx.android.synthetic.main.screen_on_boarding.*

class OnBoardingScreen : BaseScreen() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(Color.TRANSPARENT, true)
        subscribeUi()
    }

    private fun subscribeUi() {
        get_started_button.setOnClickListener {
            replaceFragment(LoginScreen(), slideRightToLeft())
        }
    }
}