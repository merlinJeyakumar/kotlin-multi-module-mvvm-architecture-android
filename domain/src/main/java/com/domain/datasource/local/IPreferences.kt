package com.domain.datasource.local

import com.domain.model.user.UserResponseModel
import kotlinx.coroutines.flow.Flow

interface IPreferences {
    suspend fun updateUser(userResponseModel: UserResponseModel)
    val userModel:Flow<UserResponseModel>
}