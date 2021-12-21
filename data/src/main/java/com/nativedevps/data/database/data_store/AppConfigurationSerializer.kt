package com.nativedevps.data.database.data_store

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.nativedevps.domain.model.configuration.AppConfiguration


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
