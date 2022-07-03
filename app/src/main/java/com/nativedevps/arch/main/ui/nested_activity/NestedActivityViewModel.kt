package com.nativedevps.arch.main.ui.nested_activity

import android.app.Application
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.datasources.remote.firebase.FirebaseAuthenticationService
import com.nativedevps.support.base_class.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NestedActivityViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var firebaseAuthenticationService: FirebaseAuthenticationService

    @Inject
    lateinit var dataStoreDataSource: IDataStoreDataSource

    override fun onCreate() {}
}