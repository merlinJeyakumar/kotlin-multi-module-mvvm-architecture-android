package com.nativedevps.arch.main.ui.splash

import android.app.Application
import android.util.Log
import com.nativedevps.domain.datasources.local.SettingsConfigurationSource
import com.nativedevps.domain.datasources.remote.firebase.FirebaseAuthenticationService
import com.nativedevps.domain.model.configuration.AppConfiguration
import com.nativedevps.domain.model.configuration.UserProfile
import com.nativedevps.support.base_class.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var firebaseAuthenticationService: FirebaseAuthenticationService

    @Inject
    lateinit var settingsConfigurationSource: SettingsConfigurationSource

    val userProfile: Flow<UserProfile> get() = settingsConfigurationSource.getUserPreference()
    val appConfiguration: Flow<AppConfiguration> get() = settingsConfigurationSource.getAppConfiguration()

    override fun onCreate() {}

    fun isAuthenticated(isAuthenticated: (isAuthenticated: Boolean) -> Unit) {
        runOnNewThread {
            val userPreference: UserProfile? =
                settingsConfigurationSource.getUserPreference().firstOrNull()
            Log.e("JK", "userPreference  ${userPreference?.firstName}")
            isAuthenticated(!userPreference?.emailId.isNullOrEmpty() && firebaseAuthenticationService.getAuthenticatedUser() != null)
        }
    }
}