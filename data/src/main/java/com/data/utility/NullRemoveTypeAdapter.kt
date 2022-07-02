package com.data.utility

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.lang.Boolean

var stringTypeAdapter: TypeAdapter<String?> = object : TypeAdapter<String?>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: String?) {

        //  The following if Is the key code , When specifying serialization , If an empty string is encountered, it will be output directly null value .
        //  because Gson The default is not serialization null Of , So this is equivalent to excluding empty string fields during serialization
        if ("" == value) {
            out.nullValue()
            return
        }
        out.value(value)
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): String? {
        val peek = `in`.peek()
        if (peek == JsonToken.NULL) {
            `in`.nextNull()
            return null
        }
        /* coerce booleans to strings for backwards compatibility */return if (peek == JsonToken.BOOLEAN) {
            Boolean.toString(`in`.nextBoolean())
        } else `in`.nextString()
    }
}

var gsonTypeAdapter: Gson = GsonBuilder().registerTypeAdapter(String::class.java, stringTypeAdapter).create()
