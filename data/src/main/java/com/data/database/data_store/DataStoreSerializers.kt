package com.data.database.data_store

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.domain.model.configuration.AppConfiguration
import com.domain.model.configuration.UserProfile
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object AppConfigurationSerializer : Serializer<AppConfiguration> {
    override val defaultValue: AppConfiguration = AppConfiguration.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): AppConfiguration {
        try {
            return AppConfiguration.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: AppConfiguration, output: OutputStream) = t.writeTo(output)
}

object UserPreferencesSerializer : Serializer<UserProfile> {
    override val defaultValue: UserProfile = UserProfile.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): UserProfile {
        try {
            return UserProfile.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserProfile, output: OutputStream) = t.writeTo(output)
}
