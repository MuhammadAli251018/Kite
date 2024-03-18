package online.muhammadali.kite.common.domain

sealed interface Request {

    data class GetTokenRequest(val googleToken: String) : Request

}