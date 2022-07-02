package com.domain.datasources.remote.api

import com.domain.model.example_list.ExampleApiModel
import com.domain.model.foo_get.RequestFooGet
import com.domain.model.foo_get.ResponseFooGet
import com.domain.model.foo_patch.RequestFooPatch
import com.domain.model.foo_patch.ResponseFooPatch
import com.domain.model.foo_post.ResponseFooPost
import com.domain.model.foo_post.RequestFooPost
import com.domain.model.update_profile.UpdateProfileModel
import com.domain.model.update_profile.UpdateSendModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Query

interface RestDataSource {
    suspend fun updateProfile(updateSendModel: UpdateSendModel): UpdateProfileModel?
    suspend fun getExampleList(): ExampleApiModel?
    suspend fun fooPost(foo:String, requestFooPost: RequestFooPost): Result<ResponseFooPost>
    suspend fun fooGet(
        authorization: String,
        requestFooGet: RequestFooGet
    ): Result<ResponseFooGet>
    suspend fun fooPatch(
        authorization: String,
        requestFooPatch: RequestFooPatch,
    ): Result<ResponseFooPatch>
}