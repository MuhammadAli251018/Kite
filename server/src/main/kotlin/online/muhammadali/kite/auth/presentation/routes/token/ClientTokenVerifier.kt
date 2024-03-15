package online.muhammadali.kite.auth.presentation.routes.token

import online.muhammadali.kite.common.utl.Result

interface ClientTokenVerifier{
    fun verify(token: String): Result<UserInfo>
}
