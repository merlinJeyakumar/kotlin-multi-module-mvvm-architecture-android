package com.domain.model.registration


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RegisterRequestModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)