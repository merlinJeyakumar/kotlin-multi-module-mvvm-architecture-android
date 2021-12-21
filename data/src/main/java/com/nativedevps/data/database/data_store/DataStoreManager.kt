package com.nativedevps.data.database.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.nativedevps.domain.model.configuration.AppConfiguration
import com.nativedevps.domain.model.configuration.UserProfile


import javax.inject.Inject

open class DataStoreManager @Inject constructor(private val context: Context) {
    private val USER_DATA_STORE_FILE_NAME = "user_prefs.pb"
    private val APP_CONFIGURATION_DATA_STORE_FILE_NAME = "app_configuration_prefs.pb"

    private val Context.userPreferencesStore: DataStore<UserProfile> by dataStore(
        fileName = USER_DATA_STORE_FILE_NAME,
        serializer = UserPreferencesSerializer
    )

    private val Context.appConfigurationStore: DataStore<AppConfiguration> by dataStore(
        fileName = APP_CONFIGURATION_DATA_STORE_FILE_NAME,
        serializer = AppConfigurationSerializer
    )

    fun getUserPreference(): DataStore<UserProfile> {
        context.userPreferencesStore
        return context.userPreferencesStore
    }

    fun getDeviceConfiguration(): DataStore<AppConfiguration> {
        return context.appConfigurationStore
    }
}