package com.nativedevps.arch.main.ui.example_list

import android.app.Application
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.datasources.remote.api.RestDataSource
import com.domain.model.configuration.UserProfile
import com.domain.model.example_list.ResponseCharacterList
import com.nativedevps.support.base_class.BaseViewModel
import com.nativedevps.support.utility.threading.runOnAsyncThread
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExampleListViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var restDataSource: RestDataSource

    @Inject
    lateinit var dataStoreDataSource: IDataStoreDataSource

    val userProfile: Flow<UserProfile> get() = dataStoreDataSource.getUserPreference()

    override fun onCreate() {
    }

    fun retrieveExampleList(
        callback: (
            boolean: Boolean,
            ResponseCharacterList?,
            error: String?
        ) -> Unit
    ) {
        currentJob = runOnAsyncThread() {

        }
    }


    private var currentJob: Job? = null
}