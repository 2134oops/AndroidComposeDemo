package com.rc.base.model.stateModel

import com.rc.base.model.error.ErrorState

sealed class ResultState<T> {
    abstract val data: T?

    data class OnInit<T : Any>(override val data: T? = null) : ResultState<T>()
    data class OnSuccess<T : Any>(override val data: T) : ResultState<T>()
    data class OnError<T>(val error: ErrorState, override val data: T? = null) : ResultState<T>()
    data class OnLoading<T>(override val data: T? = null) : ResultState<T>()
}

inline fun <T : Any> ResultState<T>.onSuccess(block: (T) -> Unit): ResultState<T> {
    if (this is ResultState.OnSuccess) {
        block(data)
    }
    return this
}

inline fun <T : Any> ResultState<T>.onError(block: (ErrorState) -> Unit): ResultState<T> {
    if (this is ResultState.OnError) {
        block(error)
    }
    return this
}

inline fun <T : Any> ResultState<T>.onLoading(block: () -> Unit): ResultState<T> {
    if (this is ResultState.OnLoading) {
        block()
    }
    return this
}

inline fun <T : Any> ResultState<T>.onInit(block: () -> Unit): ResultState<T> {
    if (this is ResultState.OnInit) {
        block()
    }
    return this
}