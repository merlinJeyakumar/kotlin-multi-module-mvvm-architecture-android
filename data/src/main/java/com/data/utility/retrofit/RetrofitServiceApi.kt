package com.data.utility.retrofit

import data.BuildConfig
import retrofit2.Retrofit

open class RetrofitServiceApi : BaseApi() {
    val instance: Retrofit
        get() {
            return retrofitBuilder.build()
        }

    override fun url(): String {
        return BuildConfig.REST_URL
    }
}