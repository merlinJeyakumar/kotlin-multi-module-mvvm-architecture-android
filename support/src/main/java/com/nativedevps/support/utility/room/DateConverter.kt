package com.nativedevps.support.utility.room

import androidx.room.TypeConverter
import org.joda.time.DateTime

class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): DateTime? {
        return if (timestamp == null) null else DateTime(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: DateTime?): Long? {
        return date?.millis
    }
}