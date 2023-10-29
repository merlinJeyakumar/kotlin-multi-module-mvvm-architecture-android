package com.data.repositories.rest.user

import com.domain.datasource.rest.UserApiService
import com.domain.model.registration.RegisterRequestModel
import com.domain.model.registration.RegisterResponseModel
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
class RegisterUserUseCase @Inject constructor(
    private val exchangeApiService: UserApiService
) : FlowUseCase<RegisterRequestModel, RegisterResponseModel>() {

    override fun performAction(parameters: RegisterRequestModel): Flow<NetworkResult<RegisterResponseModel>> {
        return emulate {
            exchangeApiService.register(parameters).emulateNetworkCall()
        }
    }
}