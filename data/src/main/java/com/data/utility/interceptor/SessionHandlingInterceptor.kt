package com.data.utility.interceptor

import com.domain.datasources.event.SessionEvent
import okhttp3.Interceptor
import okhttp3.Response
import org.greenrobot.eventbus.EventBus

class SessionHandlingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (response.code() == 401) {
            EventBus.getDefault().post(SessionEvent())
        }
        return response
    }
}