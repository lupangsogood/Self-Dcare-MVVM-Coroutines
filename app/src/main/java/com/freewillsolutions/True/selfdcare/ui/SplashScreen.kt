package com.freewillsolutions.True.selfdcare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.base.BaseScreen
import com.freewillsolutions.True.selfdcare.utility.extensions.fadeIn
import com.freewillsolutions.True.selfdcare.utility.launchMainThread
import com.jozzee.android.core.fragment.replaceFragment
import kotlinx.coroutines.delay

class SplashScreen : BaseScreen() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.screen_splash, container, false)
    }

    override fun onResume() {
        super.onResume()

        launchMainThread {
            delay(2000)
            replaceFragment(OnBoardingScreen(), fadeIn(), clearStack = true, addToBackStack = false)
        }
    }
}