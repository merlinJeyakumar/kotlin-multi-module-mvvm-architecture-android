package com.nativedevps.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.nativedevps.data.database.data_store.DataStoreManager
import com.nativedevps.data.repositories.local.configuration.DataStoreRepository
import com.nativedevps.data.repositories.local.configuration.PreferencesRepository
import com.nativedevps.domain.datasources.local.IPreferencesDataSource
import com.nativedevps.domain.datasources.local.SettingsConfigurationSource
import com.nativedevps.domain.model.configuration.AppConfiguration
import com.nativedevps.domain.model.configuration.UserProfile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ConfigurationModule {

    @Provides
    fun provideSettingsConfigurationRepository(
        userPreferenceDataStore: DataStore<UserProfile>,
        appConfiguration: DataStore<AppConfiguration>,
    ): SettingsConfigurationSource {
        return DataStoreRepository(userPreferenceDataStore,
            appConfiguration)
    }

    @Provides
    fun provideUserPreferences(dataStoreManager: DataStoreManager): DataStore<UserProfile> {
        return dataStoreManager.getUserPreference()
    }

    @Provides
    fun provideConfiguration(dataStoreManager: DataStoreManager): DataStore<AppConfiguration> {
        return dataStoreManager.getDeviceConfiguration()
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(@ApplicationContext context: Context): IPreferencesDataSource {
        return PreferencesRepository(context)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}