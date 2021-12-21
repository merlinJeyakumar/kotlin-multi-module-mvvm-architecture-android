package com.nativedevps.domain.model.update_profile

class UpdateSendModel(
    var id: Int?,
    var firstName: String?,
    var emailId: String?,
    var lastName: String?,
) {
    fun validate(): String? {
        if (this.emailId.isNullOrEmpty()) {
            return "firstName cannot be empty"
        }
        if (this.firstName.isNullOrEmpty()) {
            return "firstName cannot be empty"
        }
        if (this.lastName.isNullOrEmpty()) {
            return "lastName cannot be empty"
        } else {
            return null
        }
    }
}