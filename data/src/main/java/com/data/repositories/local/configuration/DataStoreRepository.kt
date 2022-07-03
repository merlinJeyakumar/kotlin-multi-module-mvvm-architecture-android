package com.data.repositories.local.configuration

import androidx.datastore.core.DataStore
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.model.configuration.AppConfiguration
import com.domain.model.configuration.UserProfile
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val userPreferenceDataStore: DataStore<UserProfile>,
    private val appConfigurationDataStore: DataStore<AppConfiguration>,
) : IDataStoreDataSource {

    override fun getUserPreference(): Flow<UserProfile> {
        return userPreferenceDataStore.data
    }

    override fun getAppConfiguration(): Flow<AppConfiguration> {
        return appConfigurationDataStore.data
    }

    override suspend fun updateAppConfiguration(callback: (AppConfiguration.Builder) -> AppConfiguration) {
        appConfigurationDataStore.updateData {
            callback(it.toBuilder())
        }
    }

    override suspend fun clearAppConfiguration() {
        userPreferenceDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }

    override suspend fun updateUserPreference(callback: (UserProfile.Builder) -> UserProfile) {
        userPreferenceDataStore.updateData {
            callback(it.toBuilder())
        }
    }

    override suspend fun clearUserPreference() {
        userPreferenceDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}