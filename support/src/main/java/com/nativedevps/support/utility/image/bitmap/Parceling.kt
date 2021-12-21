package com.nativedevps.support.utility.image.bitmap

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream


object Parceling {
    fun Bitmap.toBase64(): String? {
        var byteArrayOutputStream: ByteArrayOutputStream? = null
        try {
            byteArrayOutputStream = ByteArrayOutputStream()
            compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val bytes: ByteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            byteArrayOutputStream?.close()
        }
        return null
    }
}