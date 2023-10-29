package com.domain.datasource.rest

import com.domain.model.latest_currency.LatestCurrencyResponseModel
import com.domain.model.registration.RegisterRequestModel
import com.domain.model.registration.RegisterResponseModel
import com.domain.model.update.UpdateResponseModel
import com.domain.model.user.UserResponseModel
import com.domain.model.user_list.UsersListResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.QueryMap

/*
* Reference: {@link https://reqres.in/api-docs/#/}
* Sample methods
**/
interface UserApiService {
    @Headers("Content-Type: application/json")
    @GET("latest.json")
    suspend fun fetchLatestCurrency(@QueryMap queryMap: Map<String, String>): Result<LatestCurrencyResponseModel>

    @Headers("Content-Type: application/json")
    @GET("users/{id}")
    suspend fun user(@Path("id") id: Int): Result<UserResponseModel>

    @Headers("Content-Type: application/json")
    @GET("users")
    suspend fun userList(
        @QueryMap queryMap: Map<String, String>
    ): Result<UsersListResponseModel>

    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(@Body registerRequestModel: RegisterRequestModel): Result<RegisterResponseModel>

    @Headers("Content-Type: application/json")
    @PUT("users/{id}")
    suspend fun updateWithPut(@Path("id") id: Int): Result<UpdateResponseModel>

    @Headers("Content-Type: application/json")
    @PATCH("users/{uuid}")
    suspend fun updateWithPatch(@Path("id") id: Int): Result<UpdateResponseModel>
}