package online.muhammadali.kite.common.domain

sealed interface Response {
    data object UnauthorizedRequest : Response
}