package com.freewillsolutions.True.selfdcare.ui.base

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import com.freewillsolutions.True.selfdcare.utility.AppPreference
import com.freewillsolutions.True.selfdcare.utility.extensions.applyLanguage
import com.freewillsolutions.True.selfdcare.utility.extensions.applyUiMode

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        val language = AppPreference.getLanguage(newBase)
        val uiMode = AppPreference.getUiMode(newBase)
        super.attachBaseContext(newBase.applyLanguage(language).applyUiMode(uiMode))
    }

    /**
     * Update configuration for language and night mode.
     */
    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }
}