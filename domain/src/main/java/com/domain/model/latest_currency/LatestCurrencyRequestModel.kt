package com.domain.model.latest_currency


import androidx.annotation.Keep

@Keep
class LatestCurrencyRequestModel(
    var appId: String
) {
    val queryMap: Map<String, String>
        get() {
            return mutableMapOf<String, String>().also {
                it.put("app_id", appId)
            }
        }
}