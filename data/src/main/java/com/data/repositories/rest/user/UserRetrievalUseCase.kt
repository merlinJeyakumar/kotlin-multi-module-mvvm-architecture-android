package com.data.repositories.rest.user

import com.domain.datasource.rest.UserApiService
import com.domain.model.update.UpdateResponseModel
import com.domain.model.user.UserRequestModel
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
class UserRetrievalUseCase @Inject constructor(
    private val exchangeApiService: UserApiService
) : FlowUseCase<UserRequestModel, UpdateResponseModel>() {

    override fun performAction(parameters: UserRequestModel): Flow<NetworkResult<UpdateResponseModel>> {
        return emulate {
            exchangeApiService.updateWithPut(parameters.id).emulateNetworkCall() //put method
            exchangeApiService.updateWithPatch(parameters.id).emulateNetworkCall() //patch method
        }
    }
}