package com.nativedevps.data.repositories.local.configuration

import androidx.datastore.core.DataStore
import com.nativedevps.domain.datasources.local.SettingsConfigurationSource
import com.nativedevps.domain.model.configuration.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val userPreferenceDataStore: DataStore<UserProfile>,
    private val appConfigurationDataStore: DataStore<AppConfiguration>,
) : SettingsConfigurationSource {
    override fun getUserPreference(): Flow<UserProfile> {
        return userPreferenceDataStore.data
    }

    override suspend fun updateUserPreference(
        userProfile: UserProfile,
    ) {
        userPreferenceDataStore.updateData {
            it.toBuilder().mergeFrom(userProfile).build()
        }
    }

    override suspend fun clearUserPreference() {
        userPreferenceDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }

    override fun getAppConfiguration(): Flow<AppConfiguration> {
        return appConfigurationDataStore.data
    }

    override suspend fun setAppConfiguration(appConfiguration: AppConfiguration) {
        appConfigurationDataStore.updateData {
            it.toBuilder().mergeFrom(appConfiguration).build()
        }
    }
}