package com.rc.base.model.error

data class ErrorState(
    val errorCode: String,
    val message: String,
    val error: Throwable? = null
) {

    companion object {
        fun defaultError() = ErrorState("99999", "Unknown error")

        fun customError(errorCode: String, message: String) = ErrorState(errorCode, message)

        fun throwable(throwable: Throwable) =
            ErrorState("99999", "Unknown error", error = throwable)
    }
}
