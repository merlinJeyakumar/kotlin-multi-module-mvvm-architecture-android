package com.nativedevps.mlm.main.ui.main


import android.app.Application
import com.nativedevps.domain.datasources.local.SettingsConfigurationSource
import com.nativedevps.domain.model.configuration.UserProfile
import com.nativedevps.support.base_class.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var settingsConfigurationSource: SettingsConfigurationSource

    val userProfile: Flow<UserProfile> get() = settingsConfigurationSource.getUserPreference()

    override fun onCreate() {
    }
}