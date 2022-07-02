package com.nativedevps.arch.main.ui.main


import android.app.Application
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.datasources.remote.api.RestDataSource
import com.domain.model.UserModel
import com.domain.model.configuration.UserProfile
import com.domain.model.update_profile.UpdateSendModel
import com.nativedevps.support.base_class.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    @Inject
    lateinit var restDataSource: RestDataSource

    @Inject
    lateinit var dataStoreDataSource: IDataStoreDataSource

    val userProfile: Flow<UserProfile> get() = dataStoreDataSource.getUserPreference()

    override fun onCreate() {
    }
}