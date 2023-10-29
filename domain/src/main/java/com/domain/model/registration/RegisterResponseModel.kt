package com.domain.model.registration


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RegisterResponseModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("token")
    val token: String
)