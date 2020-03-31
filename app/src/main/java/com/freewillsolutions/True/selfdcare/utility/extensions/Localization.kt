package com.freewillsolutions.True.selfdcare.utility.extensions

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.util.Log
import com.freewillsolutions.True.selfdcare.utility.ENGLISH_LANGUAGE
import java.util.*

object Localization {
    var currentLanguage: String = ENGLISH_LANGUAGE
}

@Suppress("DEPRECATION")
fun Context?.applyLanguage(language: String?): Context? {
    if (this == null) return this

    val baseLanguage: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.resources.configuration.locales.get(0).language
    } else {
        this.resources.configuration.locale.language
    }

    if (language?.isNotBlank() == true && !baseLanguage.equals(language, ignoreCase = true)) {
        Localization.currentLanguage = language

        val config = Configuration(this.resources.configuration)
        val newLocale = Locale(language)
        config.setLocale(newLocale)
        Locale.setDefault(newLocale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeList = LocaleList(newLocale)
            LocaleList.setDefault(localeList)
            config.setLocales(localeList)
        }

        Log.d("Localization", "Set Language to: ${Localization.currentLanguage}")
        return this.createConfigurationContext(config)
    }
    return this
}