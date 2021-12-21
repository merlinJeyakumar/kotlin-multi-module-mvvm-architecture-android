package com.nativedevps.support.utility.device.storage_access_framework

import android.app.Activity
import android.content.Intent
import android.os.Build
import com.nativedevps.support.utility.device.mime.MimeWildCard.MIME_IMAGE_WILD_CARD

object Utility {
    fun Activity.showGalleryPicker(mime: String = MIME_IMAGE_WILD_CARD, reqCode: Int) {
        if (Build.VERSION.SDK_INT >= 19) {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = mime
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, reqCode)
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = mime
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, reqCode)
        }
    }
}