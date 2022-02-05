package com.nativedevps.arch.main.di

import com.data.di.ConfigurationModule
import com.data.di.DatabaseModule
import com.data.di.NetworkModule
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