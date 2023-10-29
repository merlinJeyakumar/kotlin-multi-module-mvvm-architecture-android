package com.data.repositories.rest.user

import com.domain.datasource.rest.UserApiService
import com.domain.model.update.UserUpdateRequestModel
import com.domain.model.user.UserResponseModel
import com.nativedevps.support.base_class.FlowUseCase
import com.nativedevps.support.coroutines.NetworkResult
import com.nativedevps.support.utility.networking.emulate
import com.nativedevps.support.utility.networking.emulateNetworkCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Injecting ApiService
 * @return result with status of success/failure {@link NetworkResult}
 */
class UserUpdateUseCase @Inject constructor(
    private val exchangeApiService: UserApiService
) : FlowUseCase<UserUpdateRequestModel, UserResponseModel>() {

    override fun performAction(parameters: UserUpdateRequestModel): Flow<NetworkResult<UserResponseModel>> {
        return emulate {
            exchangeApiService.user(parameters.id).emulateNetworkCall()
        }
    }
}