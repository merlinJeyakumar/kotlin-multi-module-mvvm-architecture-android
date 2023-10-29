package com.domain.model.user_list


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserListRequestModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int
) {
    fun asQueryMap(): Map<String, String> {
        return mapOf(
            "page" to page.toString(),
            "per_page" to perPage.toString(),
        )
    }
}