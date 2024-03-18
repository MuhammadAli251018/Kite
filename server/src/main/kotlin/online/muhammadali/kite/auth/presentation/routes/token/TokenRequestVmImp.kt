package online.muhammadali.kite.auth.presentation.routes.token

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import online.muhammadali.kite.auth.data.util.security.JwtGenerator
import online.muhammadali.kite.auth.domain.models.TokenConfig
import online.muhammadali.kite.auth.domain.repositories.UsersDBRepo
import online.muhammadali.kite.common.domain.User
import online.muhammadali.kite.common.utl.Failure
import online.muhammadali.kite.common.utl.Result
import online.muhammadali.kite.common.utl.Success
import org.bson.types.ObjectId


class TokenRequestVmImp(
    private val usersDBRepo: UsersDBRepo,
    private val verifier: ClientTokenVerifier,
    private val tokenGenerator: JwtGenerator,
    private val jwtConfig: TokenConfig
) :  TokenRequestViewModel {

    override fun verifyToken(token: String): Result<UserInfo> {
        return verifier.verify(token)
    }

    override fun getUserId(email: String, name: String): Flow<Result<String>> = flow {
        usersDBRepo.getUserByEmail(email).collectLatest { result ->
            if (result is Success)
                emit(Success(result.data.id))
            else {
                val id = ObjectId().toString()
                usersDBRepo.addNewUser(user = User(id = id, name = name, email = email)).collectLatest {
                    if (it is Success)
                        emit(Success(id))
                    else
                        emit(Failure(Exception("Couldn't add user")))
                }
            }
        }
    }

    override fun generateToken(id: String, email: String, name: String): String {

        return tokenGenerator.generateToken(
            config = jwtConfig,
            data = User(
            id = id,
            email = email,
            name = name
        ))
    }
}