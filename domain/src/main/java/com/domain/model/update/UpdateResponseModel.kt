package com.domain.model.update


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UpdateResponseModel(
    @SerializedName("updatedAt")
    val updatedAt: String
)