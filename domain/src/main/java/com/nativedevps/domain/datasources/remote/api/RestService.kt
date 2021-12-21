package com.nativedevps.domain.datasources.remote.api

import com.nativedevps.domain.model.example_list.ExampleApiModel
import com.nativedevps.domain.model.update_profile.UpdateProfileModel
import com.nativedevps.domain.model.update_profile.UpdateSendModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestService {
    @POST("update") //by retrofit configured url
    suspend fun update(@Body updateSendModel: UpdateSendModel): UpdateProfileModel?

    @GET("http://hp-api.herokuapp.com/api/characters") //dynamic url
    suspend fun getExampleList(): ExampleApiModel?
}