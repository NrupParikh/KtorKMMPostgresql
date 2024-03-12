package com.nrup.ktor.backend.config

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.nrup.ktor.backend.base.BaseResponse
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<MismatchedInputException> { call, _ ->
            val error = BaseResponse.ErrorResponse("Invalid request data format")
            call.respond(error.statusCode, error)
        }
        exception { call: ApplicationCall, cause: JsonParseException ->
            call.respond(BaseResponse.ErrorResponse(cause.message!!))
        }
    }
}