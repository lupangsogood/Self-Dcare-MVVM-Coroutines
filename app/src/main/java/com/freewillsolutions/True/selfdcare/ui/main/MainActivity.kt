package com.freewillsolutions.True.selfdcare.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.freewillsolutions.True.selfdcare.BuildConfig
import com.freewillsolutions.True.selfdcare.R
import com.freewillsolutions.True.selfdcare.ui.SplashScreen
import com.freewillsolutions.True.selfdcare.ui.base.BaseActivity
import com.jozzee.android.core.connection.NetworkMonitor
import com.jozzee.android.core.connection.NetworkStatus
import com.jozzee.android.core.fragment.FragmentContainer
import com.jozzee.android.core.fragment.replaceFragment
import com.jozzee.android.core.getConnectivityManager
import com.jozzee.android.core.simpleName
import com.jozzee.android.core.utility.Logger

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initial logger show log when is debug mode.
        Logger.isLogging = BuildConfig.DEBUG

        //Listener network connection.
        NetworkMonitor(getConnectivityManager()).observe(this, Observer(::onNetworkChanged))

        setContentView(R.layout.activity_main)
        FragmentContainer.id = R.id.main_fragment_container
        if (savedInstanceState == null) {
            replaceFragment(SplashScreen(), clearStack = true, addToBackStack = false)
        }
    }

    private fun onNetworkChanged(status: NetworkStatus) {
        Logger.warning(simpleName(), "Network Changed: $status")
        //TODO("Do something when network changed.")
    }
}
