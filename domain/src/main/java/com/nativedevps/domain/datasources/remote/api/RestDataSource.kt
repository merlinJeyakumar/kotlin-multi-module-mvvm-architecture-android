package com.nativedevps.domain.datasources.remote.api

import com.nativedevps.domain.model.example_list.ExampleApiModel
import com.nativedevps.domain.model.update_profile.UpdateProfileModel
import com.nativedevps.domain.model.update_profile.UpdateSendModel

interface RestDataSource {
    suspend fun updateProfile(updateSendModel: UpdateSendModel): UpdateProfileModel?
    suspend fun getExampleList(): ExampleApiModel?
}