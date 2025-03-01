package com.rc.base.network

sealed class NetworkResponse<out Data : Any, out Error : Any> {
    data class Success<Data : Any>(val body: Data) : NetworkResponse<Data, Nothing>()

    data class ApiError<Error : Any>(val errorCode: Int, val errorBody: Error) :
        NetworkResponse<Nothing, Error>()

    data class NetworkError(val error: Throwable) : NetworkResponse<Nothing, Nothing>()

    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}