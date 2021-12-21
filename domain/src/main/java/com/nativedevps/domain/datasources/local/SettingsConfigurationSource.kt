package com.nativedevps.domain.datasources.local

import com.nativedevps.domain.model.configuration.*
import kotlinx.coroutines.flow.Flow


interface SettingsConfigurationSource {
    fun getUserPreference(): Flow<UserProfile>
    suspend fun updateUserPreference(
        userProfile: UserProfile,
    )
    suspend fun clearUserPreference()

    fun getAppConfiguration(): Flow<AppConfiguration>
    suspend fun setAppConfiguration(appConfiguration: AppConfiguration)
}
