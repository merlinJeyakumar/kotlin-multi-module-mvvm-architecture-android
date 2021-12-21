package com.nativedevps.domain.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.nativedevps.domain.model.configuration.UserProfile

@Keep
open class UserModel {

    @SerializedName("emailId")
    var emailId: String? = null

    @SerializedName("firstName")
    var firstName: String? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("lastName")
    var lastName: String? = null

    fun toProfile(userProfile: UserProfile = UserProfile.getDefaultInstance()): UserProfile {
        return userProfile.toBuilder()
            .setId(id)
            .setFirstName(firstName ?: "")
            .setLastName(lastName ?: "")
            .setEmailId(emailId)
            .build()
    }
}