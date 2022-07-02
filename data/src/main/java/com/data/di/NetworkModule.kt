package com.data.di

import com.data.repositories.remote.api.RestRepository
import com.data.repositories.remote.firebase.authentication.FirebaseAuthenticationRepository
import com.data.utility.retrofit.RetrofitServiceApi
import com.domain.datasources.remote.api.ApiRestService
import com.domain.datasources.remote.api.RestDataSource
import com.domain.datasources.remote.firebase.FirebaseAuthenticationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun firebaseRepository(apiRestService: ApiRestService): RestDataSource {
        return RestRepository(apiRestService)
    }

    @Provides
    @Singleton
    fun provideApiService(): RetrofitServiceApi {
        return RetrofitServiceApi()
    }

    @Provides
    @Singleton
    fun provideApiRestService(retrofitServiceApi: RetrofitServiceApi): ApiRestService {
        return retrofitServiceApi.instance.create(ApiRestService::class.java)
    }
}