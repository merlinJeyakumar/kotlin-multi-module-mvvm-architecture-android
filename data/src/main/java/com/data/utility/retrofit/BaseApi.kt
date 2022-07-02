package com.data.utility.retrofit

import com.data.utility.Constant
import com.data.utility.exception.ResultCallAdapterFactory
import com.data.utility.interceptor.DefaultHeaderInterceptor
import com.data.utility.interceptor.SessionHandlingInterceptor
import com.nativedevps.support.utility.debugging.JLogI
import com.nativedevps.support.utility.retrofit.gsonTypeAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseApi {
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        .connectTimeout(Constant.TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .readTimeout(Constant.TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .writeTimeout(Constant.TIMEOUT_REQUEST, TimeUnit.SECONDS)

    val retrofitBuilder: Retrofit.Builder
        get() {
            return Retrofit.Builder()
                .baseUrl(url())
                .client(httpClient.apply {
                    addInterceptor(HttpLoggingInterceptor {
                        JLogI(it)
                    }.setLevel(HttpLoggingInterceptor.Level.BODY))
                    addInterceptor(DefaultHeaderInterceptor())
                    addInterceptor(SessionHandlingInterceptor())
                }.build())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        gsonTypeAdapter
                    )
                )
                .addCallAdapterFactory(ResultCallAdapterFactory())
        }

    abstract fun url(): String
}