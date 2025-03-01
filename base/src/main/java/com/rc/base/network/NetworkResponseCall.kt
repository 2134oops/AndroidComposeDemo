package com.rc.base.network

import okhttp3.Request
import okhttp3.ResponseBody
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.lang.reflect.Type

/*
* Reference https://github.com/haroldadmin/NetworkResponseAdapter
* */

internal class NetworkResponseCall<S : Any, E : Any>(
    private val backingCall: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>,
    private val successBodyType: Type
) : Call<NetworkResponse<S, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        backingCall.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (e: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(code, errorBody)),
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                throwable.printStackTrace()
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.NetworkError(throwable)
                    else -> NetworkResponse.UnknownError(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }

        })
    }

    override fun clone(): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(
            backingCall = backingCall,
            errorConverter = errorConverter,
            successBodyType = successBodyType
        )
    }

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("use enqueue method")
    }

    override fun isExecuted(): Boolean = backingCall.isExecuted

    override fun cancel() {
        backingCall.cancel()
    }

    override fun isCanceled(): Boolean = backingCall.isCanceled

    override fun request(): Request = backingCall.request()

    override fun timeout(): Timeout = backingCall.timeout()


}