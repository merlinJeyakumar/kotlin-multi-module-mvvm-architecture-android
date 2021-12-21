package com.nativedevps.support.utility.image.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.InputStream


object Utility {
    @Throws(OutOfMemoryError::class)
    fun Context.getBitmap(uri: Uri): Bitmap? {
        var inputStream: InputStream? = null
        try {
            inputStream = contentResolver.openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream)
        } finally {
            inputStream?.close()
        }
    }
}