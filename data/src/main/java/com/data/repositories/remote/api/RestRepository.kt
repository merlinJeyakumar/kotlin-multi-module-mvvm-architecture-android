package com.data.repositories.remote.api

import com.domain.datasources.remote.api.ApiRestService
import com.domain.datasources.remote.api.RestDataSource
import com.domain.model.example_list.ResponseCharacterList
import com.domain.model.foo_get.RequestFooGet
import com.domain.model.foo_get.ResponseFooGet
import com.domain.model.foo_patch.RequestFooPatch
import com.domain.model.foo_patch.ResponseFooPatch
import com.domain.model.foo_post.RequestFooPost
import com.domain.model.foo_post.ResponseFooPost
import com.domain.model.update_profile.UpdateProfileModel
import com.domain.model.update_profile.UpdateSendModel
import javax.inject.Inject

/*
* implement your data source here, if multiple implement here & inject your service in constructor
* */
class RestRepository @Inject constructor(private val apiRestService: ApiRestService) :
    RestDataSource {
    override suspend fun updateProfile(updateSendModel: UpdateSendModel): UpdateProfileModel? {
        return apiRestService.update() //no parameter required for this api.
    }

    override suspend fun getCharacterList(): Result<ResponseCharacterList> {
        return apiRestService.getCharacterList()
    }

    override suspend fun fooPost(
        foo: String,
        requestFooPost: RequestFooPost,
    ): Result<ResponseFooPost> {
        return apiRestService.fooPost(foo, requestFooPost)
    }

    override suspend fun fooGet(
        authorization: String,
        requestFooGet: RequestFooGet
    ): Result<ResponseFooGet> {
        return apiRestService.fooGet(authorization, requestFooGet.foo, requestFooGet.bar)
    }

    override suspend fun fooPatch(
        authorization: String,
        requestFooPatch: RequestFooPatch
    ): Result<ResponseFooPatch> {
        return apiRestService.fooPatch(authorization, requestFooPatch)
    }
}