package com.data.di

import com.google.gson.GsonBuilder
import com.data.repositories.remote.api.RestRepository
import com.data.repositories.remote.firebase.FirebaseAuthenticationRepository
import com.data.utility.Constant
import com.domain.datasources.remote.api.RestDataSource
import com.domain.datasources.remote.api.RestService
import com.domain.datasources.remote.firebase.FirebaseAuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun firebaseAuthentication(): FirebaseAuthenticationService {
        return FirebaseAuthenticationRepository()
    }

    @Provides
    @Singleton
    fun firebaseRepository(restService: RestService): RestDataSource {
        return RestRepository(restService)
    }

    @Provides
    @Singleton
    fun retrofitInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(Constant.TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .readTimeout(Constant.TIMEOUT_REQUEST, TimeUnit.SECONDS)
            .writeTimeout(Constant.TIMEOUT_REQUEST, TimeUnit.SECONDS)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.REST_URL)
            .client(httpClient.apply { addInterceptor(HttpLoggingInterceptor()) }.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRestService(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }
}