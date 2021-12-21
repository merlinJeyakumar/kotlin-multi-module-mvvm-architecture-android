package com.nativedevps.domain.model

import androidx.annotation.Keep

@Keep
open class BaseResponse {
    var message: String? = null
    var status: Int = 0
}