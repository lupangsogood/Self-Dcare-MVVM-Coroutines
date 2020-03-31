package com.freewillsolutions.True.selfdcare.utility

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.util.*

object AppPreference {
    private const val KEY_LANGUAGE = "lang"
    private const val KEY_UI_MODE = "uiMode"

    fun getLanguage(context: Context?): String {
        if (context == null) return Locale.getDefault().language
        return createPreference(context).getString(KEY_LANGUAGE, null)
                ?: Locale.getDefault().language
    }

    fun setLanguage(activity: Activity, language: String) {
        Locale.setDefault(Locale(language))
        createPreference(activity).edit {
            putString(KEY_LANGUAGE, language)
        }
        activity.recreate()
    }

    /**
     * Return [AppCompatDelegate.MODE_NIGHT_YES],
     * [AppCompatDelegate.MODE_NIGHT_NO],
     * [AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM],
     * [AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY]
     * [AppCompatDelegate.MODE_NIGHT_UNSPECIFIED]
     * by default is 0
     */
    fun getUiMode(context: Context?): Int {
        if (context == null) return 0
        return createPreference(context).getInt(KEY_UI_MODE, 0)
    }

    fun setUiMode(activity: Activity, uiMode: Int) {
        createPreference(activity).edit {
            putInt(KEY_UI_MODE, uiMode)
        }
        activity.recreate()
    }

    fun createPreference(context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(PREFERENCE_NAME,
                MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun clear(context: Context) {
        createPreference(context).edit {
            clear()
        }
    }

}