package com.domain.datasources.remote.api

import com.domain.model.example_list.ExampleApiModel
import com.domain.model.update_profile.UpdateProfileModel
import com.domain.model.update_profile.UpdateSendModel

interface RestDataSource {
    suspend fun updateProfile(updateSendModel: UpdateSendModel): UpdateProfileModel?
    suspend fun getExampleList(): ExampleApiModel?
}