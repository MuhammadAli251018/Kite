package online.muhammadali.kite.common.utl

import io.ktor.server.application.Application

fun Application.getVariableOrThrow(path: String): String {
    return environment.config.property(path).getString()
}