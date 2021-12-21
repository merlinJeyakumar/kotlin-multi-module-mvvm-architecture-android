package com.nativedevps.domain.model.update_profile

class UpdateSendModel(
    var id: Int?,
    var firstName: String?,
    var lastName: String?,
) {
    var fatherName: String? = null
    var age: Int? = null
    var gender: String? = null
    var mobileNo: String? = null
    var emailId: String? = null
    var addressLine1: String? = null
    var addressLine2: String? = null
    var city: String? = null
    var state: String? = null
    var country: String? = null
    var postalCode: String? = null
    var aadharNo: String? = null
    var panCard: String? = null
    var profileImage: String? = null

    fun validate(): String? {
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