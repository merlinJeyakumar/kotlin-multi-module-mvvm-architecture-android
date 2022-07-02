package com.data.utility.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class DefaultHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}