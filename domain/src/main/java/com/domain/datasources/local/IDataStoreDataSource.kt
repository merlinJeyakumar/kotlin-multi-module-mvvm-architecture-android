package com.domain.datasources.local

import com.domain.model.configuration.*
import kotlinx.coroutines.flow.Flow


interface IDataStoreDataSource {
    fun getUserPreference(): Flow<UserProfile>
    suspend fun updateUserPreference(
        userProfile: UserProfile,
    )
    suspend fun clearUserPreference()

    fun getAppConfiguration(): Flow<AppConfiguration>
    suspend fun setAppConfiguration(appConfiguration: AppConfiguration)
}
