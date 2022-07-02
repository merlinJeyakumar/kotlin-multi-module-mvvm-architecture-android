package com.domain.datasources.remote.api

import com.domain.model.example_list.ExampleApiModel
import com.domain.model.foo_get.ResponseFooGet
import com.domain.model.foo_patch.RequestFooPatch
import com.domain.model.foo_patch.ResponseFooPatch
import com.domain.model.foo_post.ResponseFooPost
import com.domain.model.foo_post.RequestFooPost
import com.domain.model.update_profile.UpdateProfileModel
import retrofit2.http.*

interface ApiRestService {
    //https://jsonkeeper.com/b/YGN7
    @GET("/b/YGN7") //by retrofit configured url || add parameter when required
    suspend fun update(): UpdateProfileModel?

    @GET("http://hp-api.herokuapp.com/api/characters") //dynamic url
    suspend fun getExampleList(): ExampleApiModel?

    @POST("fooPost")
    suspend fun fooPost(
        @Header("foo") foo: String,
        @Body requestFooPost: RequestFooPost,
    ): Result<ResponseFooPost>

    @GET("fooGet")
    suspend fun fooGet(
        @Header("Authorization") authorization: String,
        @Query("foo") foo: String,
        @Query("bar") bar: String,
    ): Result<ResponseFooGet>

    @PATCH("fooPatch")
    suspend fun fooPatch(
        @Header("Authorization") authorization: String,
        @Body requestFooPatch: RequestFooPatch,
    ): Result<ResponseFooPatch>
}