package com.domain.datasources.remote.api

import com.domain.model.example_list.ResponseCharacterList
import com.domain.model.foo_get.RequestFooGet
import com.domain.model.foo_get.ResponseFooGet
import com.domain.model.foo_patch.RequestFooPatch
import com.domain.model.foo_patch.ResponseFooPatch
import com.domain.model.foo_post.ResponseFooPost
import com.domain.model.foo_post.RequestFooPost
import com.domain.model.update_profile.UpdateProfileModel
import com.domain.model.update_profile.UpdateSendModel

interface RestDataSource {
    suspend fun updateProfile(updateSendModel: UpdateSendModel): UpdateProfileModel?
    suspend fun getCharacterList(): Result<ResponseCharacterList>
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