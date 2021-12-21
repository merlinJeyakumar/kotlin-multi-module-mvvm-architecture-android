package com.nativedevps.arch.main.di

import com.nativedevps.data.di.ConfigurationModule
import com.nativedevps.data.di.DatabaseModule
import com.nativedevps.data.di.NetworkModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ConfigurationModule::class,
        DatabaseModule::class,
        NetworkModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
abstract class AppModule {

}