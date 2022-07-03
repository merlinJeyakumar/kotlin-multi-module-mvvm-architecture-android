package com.nativedevps.arch.main.ui.datastore_example

import android.app.Application
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.datasources.remote.firebase.FirebaseAuthenticationService
import com.domain.model.configuration.UserProfile
import com.nativedevps.support.base_class.BaseViewModel
import com.nativedevps.support.utility.threading.runOnAsyncThread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DataStoreExampleActivityViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var firebaseAuthenticationService: FirebaseAuthenticationService

    @Inject
    lateinit var dataStoreDataSource: IDataStoreDataSource

    val userProfile: Flow<UserProfile> get() = dataStoreDataSource.getUserPreference()

    override fun onCreate() {}

    fun update(
        firstName: String,
        lastName: String,
        email: String
    ) {
        runOnAsyncThread {
            dataStoreDataSource.updateUserPreference {
                it.apply {
                    setFirstName(firstName)
                    setLastName(lastName)
                    setEmailId(email)
                }.build()
            }
        }
    }

    fun clearUserPreference(){
        runOnAsyncThread {
            dataStoreDataSource.clearUserPreference()
        }
    }
}