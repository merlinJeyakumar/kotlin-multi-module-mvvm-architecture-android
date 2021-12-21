package com.nativedevps.data.repositories.remote.api

import com.nativedevps.domain.datasources.remote.api.RestDataSource
import com.nativedevps.domain.datasources.remote.api.RestService
import com.nativedevps.domain.model.example_list.ExampleApiModel
import com.nativedevps.domain.model.update_profile.UpdateProfileModel
import com.nativedevps.domain.model.update_profile.UpdateSendModel
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