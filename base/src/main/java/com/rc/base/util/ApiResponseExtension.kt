package com.rc.base.util

import com.rc.base.model.error.BaseError
import com.rc.base.model.error.ErrorState
import com.rc.base.model.stateModel.ResultState
import com.rc.base.network.NetworkResponse

suspend fun <Response : Any, Error : BaseError, Data : Any> NetworkResponse<Response, Error>.convertToResultState(
    block: suspend (Response) -> Data
): ResultState<Data> {
    return try {
        when (this) {
            is NetworkResponse.Success -> {
                block(body).let {
                    ResultState.OnSuccess(it)
                }
            }

            is NetworkResponse.ApiError -> {
                ResultState.OnError(ErrorState(errorBody.errorCode, errorBody.message))
            }

            else -> {
                ResultState.OnError(ErrorState.defaultError())
            }
        }
    } catch (e: Exception) {
        ResultState.OnError(ErrorState.defaultError())
    }
}