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
    lateinit var IDataStoreDataSource: IDataStoreDataSource

    val userProfile: Flow<UserProfile> get() = IDataStoreDataSource.getUserPreference()

    override fun onCreate() {
    }

    fun retrieveUserProfile(
        callback: (boolean: Boolean, UserModel?, error: String?) -> Unit, //todo: replace with specific type
    ) {
        runOnNewThread {
            showProgressDialog("Loading..")
            try {
                val profileModel = restDataSource.updateProfile(UpdateSendModel(1,
                    "Jeyakumar",
                    "s.merlinjeyakumar@gmail.com",
                    "S"))
                if (profileModel != null) {
                    IDataStoreDataSource.updateUserPreference(profileModel.result.toProfile())
                }
                runOnUiThread {
                    if (profileModel?.result != null) {
                        hideProgressDialog()
                        callback(true, profileModel.result, null) //todo
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