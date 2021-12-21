package com.nativedevps.data.database.data_store

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.nativedevps.domain.model.configuration.UserProfile


import java.io.InputStream
import java.io.OutputStream

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
