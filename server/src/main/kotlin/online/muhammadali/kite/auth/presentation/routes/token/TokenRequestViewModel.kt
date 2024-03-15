package online.muhammadali.kite.auth.presentation.routes.token

import kotlinx.coroutines.flow.Flow
import online.muhammadali.kite.common.utl.Result

interface TokenRequestViewModel {
    fun verifyToken(token: String): Result<UserInfo>

    /** if user is not registered it generates new id and adds it to db*/
    fun getUserId(email: String, name: String): Flow<Result<String>>
    fun generateToken(id: String, email: String, name: String): String
}