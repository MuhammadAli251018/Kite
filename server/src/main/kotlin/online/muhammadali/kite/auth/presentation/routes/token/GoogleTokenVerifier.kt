package online.muhammadali.kite.auth.presentation.routes.token

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import online.muhammadali.kite.common.utl.Failure
import online.muhammadali.kite.common.utl.Result
import online.muhammadali.kite.common.utl.Success

class GoogleTokenVerifier(
    private val issuer: String,
    private val audience: String
) :
    ClientTokenVerifier {

    override fun verify(token: String): Result<UserInfo> {
        return try {
            val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
                .setIssuer(issuer)
                .setAudience(listOf(audience))
                .build()
                .verify(token)

            if (verifier == null)
                throw Exception("Couldn't verify token")
            else {
                val email = verifier.payload.email ?: throw Exception("Couldn't get user email")
                Success(UserInfo(email))
            }

        } catch (e: Exception) {
            Failure(e)
        }
    }
}