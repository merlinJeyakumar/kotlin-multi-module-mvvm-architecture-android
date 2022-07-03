package com.domain.datasources.local

import com.domain.model.configuration.AppConfiguration
import com.domain.model.configuration.UserProfile
import kotlinx.coroutines.flow.Flow


interface IDataStoreDataSource {
    fun getAppConfiguration(): Flow<AppConfiguration>
    suspend fun updateAppConfiguration(callback: (AppConfiguration.Builder) -> AppConfiguration)
    suspend fun clearAppConfiguration()


    fun getUserPreference(): Flow<UserProfile>
    suspend fun updateUserPreference(callback: (UserProfile.Builder) -> UserProfile)
    suspend fun clearUserPreference()
}
