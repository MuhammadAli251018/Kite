package online.muhammadali.kite

import com.auth0.jwt.JWT
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import online.muhammadali.kite.auth.presentation.routes.token.RequestTokenData
import online.muhammadali.kite.plugins.configureRouting
import online.muhammadali.kite.plugins.configureSerialization
import org.junit.Assert.*
import org.junit.Test

class ApplicationKtTest{
    @Test
    fun `success if returns a jwt token`() = testApplication {
        application {
            configureRouting()
            configureSerialization()
        }


        /*client.get("/auth/request-token") {
            val user = RequestTokenData(userName = "Muhammad Ali", email = "muhammadali251018@gmail.com", token = "bla")
            val json: String = Json.encodeToString(user)
            setBody(json)
            contentType(ContentType.Application.Json)


        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            val result = JWT.decode(bodyAsText())
            println(result.expiresAt)
            println(result.issuer)
            println(result.claims)

            val userName = result.claims["name"]
            val email = result.claims["email"]

            assertEquals("Muhammad Ali", userName!!.asString())
            assertEquals("muhammadali251018@gmail.com", email!!.asString())
        }*/
    }
}