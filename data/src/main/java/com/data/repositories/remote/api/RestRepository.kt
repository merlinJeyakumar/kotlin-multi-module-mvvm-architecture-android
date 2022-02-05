package com.data.repositories.remote.api

import com.domain.datasources.remote.api.RestDataSource
import com.domain.datasources.remote.api.RestService
import com.domain.model.example_list.ExampleApiModel
import com.domain.model.update_profile.UpdateProfileModel
import com.domain.model.update_profile.UpdateSendModel
import javax.inject.Inject

class RestRepository @Inject constructor(private val restService: RestService) :
    RestDataSource {
    override suspend fun updateProfile(updateSendModel: UpdateSendModel): UpdateProfileModel? {
        return restService.update() //no parameter required for this api.
    }

    override suspend fun getExampleList(): ExampleApiModel? {
        return restService.getExampleList()
    }
}