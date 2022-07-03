package com.nativedevps.arch.main.ui.sql_room_example

import android.app.Application
import com.data.database.dao.flickr.SampleItemDao
import com.domain.datasources.local.IDataStoreDataSource
import com.domain.datasources.remote.firebase.FirebaseAuthenticationService
import com.domain.entity.flickr.SampleEntity
import com.nativedevps.support.base_class.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RoomSqlExampleActivityViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

    @Inject
    lateinit var firebaseAuthenticationService: FirebaseAuthenticationService

    @Inject
    lateinit var dataStoreDataSource: IDataStoreDataSource

    @Inject
    lateinit var sampleItemDao: SampleItemDao

    val flow get() = sampleItemDao.getLiveItemList

    override fun onCreate() {
    }

    fun addItem() {
        sampleItemDao.insert(SampleEntity().apply {
            this.id = UUID.randomUUID().toString()
            this.page = (1 .. 10).random()
            this.title = "Title: " + UUID.randomUUID().toString()
        })
    }

    fun removeItem(id: String) {
        sampleItemDao.delete(id)
    }

    fun dropItems() {
        sampleItemDao.clearAll()
    }
}