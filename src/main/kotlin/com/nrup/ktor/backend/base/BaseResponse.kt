package com.nrup.ktor.backend.base

import io.ktor.http.*
sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK,
    val data: T
) {
    class SuccessResponse<T>(data: T) : BaseResponse<T>(data = data)
    class ErrorResponse<T>(code: HttpStatusCode, data: T) : BaseResponse<T>(code, data)

}