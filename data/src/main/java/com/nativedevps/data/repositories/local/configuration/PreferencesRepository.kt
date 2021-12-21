package com.nativedevps.data.repositories.local.configuration

import android.content.Context
import com.nativedevps.domain.datasources.local.IPreferencesDataSource
import com.nativedevps.support.utility.shared_preference.SharedPreferences
import javax.inject.Inject


class PreferencesRepository @Inject constructor(
    private val applicationContext: Context,
) : IPreferencesDataSource {
    private val SP_NAME = "native_developers"
    private val PREFS_LANGUAGE = "PREFS_LANGUAGE"

    init {
        SharedPreferences.Builder()
            .setContext(applicationContext)
            .setMode(Context.MODE_PRIVATE)
            .setPrefsName(SP_NAME)
            .build()

    }

    override fun getLanguage(): String? {
        return SharedPreferences.getString(PREFS_LANGUAGE, null)
    }

    override fun setLanguage(string: String) {
        SharedPreferences.putString(PREFS_LANGUAGE, string)
    }

}