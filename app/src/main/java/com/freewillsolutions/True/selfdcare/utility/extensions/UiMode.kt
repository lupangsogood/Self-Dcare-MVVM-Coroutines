package com.freewillsolutions.True.selfdcare.utility.extensions

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.freewillsolutions.True.selfdcare.utility.AppPreference

fun Context?.applyUiMode(uiMode: Int): Context? {
    if (this == null) return this

    if (uiMode != 0) {
        val config = Configuration(this.resources.configuration)
        AppCompatDelegate.setDefaultNightMode(uiMode)
        config.uiMode = when (uiMode) {
            AppCompatDelegate.MODE_NIGHT_YES -> Configuration.UI_MODE_NIGHT_YES
            AppCompatDelegate.MODE_NIGHT_NO -> Configuration.UI_MODE_NIGHT_NO
            else -> config.uiMode
        }
        Log.d("UiMode", "Set ui mode: ${config.uiMode}")
        return this.createConfigurationContext(config)
    }
    return this
}

fun Context.isUiModeNight(): Boolean {
    return AppPreference.getUiMode(this).let { mode ->
        (mode == AppCompatDelegate.MODE_NIGHT_YES ||
                ((mode == 0 || mode == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) &&
                        this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES))
    }
}
