package com.nativedevps.arch.main.ui.splash

import android.app.Application
import android.util.Log
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.datasources.remote.firebase.FirebaseAuthenticationService
import com.domain.model.configuration.AppConfiguration
import com.domain.model.configuration.UserProfile
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
    lateinit var IDataStoreDataSource: IDataStoreDataSource

    val userProfile: Flow<UserProfile> get() = IDataStoreDataSource.getUserPreference()
    val appConfiguration: Flow<AppConfiguration> get() = IDataStoreDataSource.getAppConfiguration()

    override fun onCreate() {}

    fun isAuthenticated(isAuthenticated: (isAuthenticated: Boolean) -> Unit) {
        runOnNewThread {
            val userPreference: UserProfile? =
                IDataStoreDataSource.getUserPreference().firstOrNull()
            Log.e("JK", "userPreference  ${userPreference?.firstName}")
            isAuthenticated(!userPreference?.emailId.isNullOrEmpty() && firebaseAuthenticationService.getAuthenticatedUser() != null)
        }
    }
}