package com.nativedevps.arch.main.ui.example_fragment


import android.app.Application
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.datasources.local.IPreferencesDataSource
import com.domain.datasources.remote.api.RestDataSource
import com.nativedevps.support.base_class.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExampleFragmentViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var restDataSource: RestDataSource

    @Inject
    lateinit var dataStoreDataSource: IDataStoreDataSource

    @Inject
    lateinit var preferencesDataSource: IPreferencesDataSource

    override fun onCreate() {
        //noop
    }

}