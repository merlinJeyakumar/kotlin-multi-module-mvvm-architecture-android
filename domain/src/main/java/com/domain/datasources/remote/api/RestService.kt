package com.domain.datasources.remote.api

import com.domain.model.example_list.ExampleApiModel
import com.domain.model.update_profile.UpdateProfileModel
import retrofit2.http.GET

interface RestService {
    //https://jsonkeeper.com/b/YGN7
    @GET("/b/YGN7") //by retrofit configured url || add parameter when required
    suspend fun update(): UpdateProfileModel?

    @GET("http://hp-api.herokuapp.com/api/characters") //dynamic url
    suspend fun getExampleList(): ExampleApiModel?
}