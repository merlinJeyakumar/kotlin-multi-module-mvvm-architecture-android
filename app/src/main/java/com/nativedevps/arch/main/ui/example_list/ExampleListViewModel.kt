package com.nativedevps.arch.main.ui.example_list

import android.app.Application
import com.nativedevps.domain.datasources.local.SettingsConfigurationSource
import com.nativedevps.domain.datasources.remote.api.RestDataSource
import com.nativedevps.domain.model.configuration.UserProfile
import com.nativedevps.domain.model.example_list.ExampleApiModel
import com.nativedevps.support.base_class.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExampleListViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var restDataSource: RestDataSource

    @Inject
    lateinit var settingsConfigurationSource: SettingsConfigurationSource

    val userProfile: Flow<UserProfile> get() = settingsConfigurationSource.getUserPreference()

    override fun onCreate() {
    }

    fun retrieveExampleList(
        callback: (boolean: Boolean, ExampleApiModel?, error: String?) -> Unit, //todo: replace with specific type
    ) {
        runOnNewThread {
            showProgressDialog("Loading..")
            try {
                val exampleList = restDataSource.getExampleList()
                runOnUiThread {
                    if (!exampleList.isNullOrEmpty()) {
                        hideProgressDialog()
                        callback(true, exampleList, null) //todo
                    } else {
                        throw IllegalStateException("invalid result")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    hideProgressDialog()
                    callback(false, null, "retrieving failed ${e.localizedMessage}")
                }
            }
        }
    }
}