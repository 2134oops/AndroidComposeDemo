package com.example.observatory.network

import com.rc.base.model.error.BaseError

data class BaseErrorResponse(
    override val errorCode: String,
    override val message: String,
): BaseError()