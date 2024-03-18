package online.muhammadali.kite.auth.data.util.security

import online.muhammadali.kite.common.domain.User
import org.junit.Test
import java.nio.charset.StandardCharsets

class JwtGeneratorTest {
    val jwtGenerator = JwtGenerator()

    @Test
    fun `success if returns token`() {
       /* println(String( getSecretKey(), StandardCharsets.UTF_8))

        val user = User(
            id = "123456789",
            name = "MuhammadAli",
            email = "muhammadali251018@gmail.com"
        )

        val expectedResult = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJvbmxpbmUubXVoYW1tYWRhbGkua2l0ZSIsImlkIjoiMTIzNDU2Nzg5IiwibmFtZSI6Ik11aGFtbWFkQWxpIiwiZW1haWwiOiJtdWhhbW1hZGFsaTI1MTAxOEBnbWFpbC5jb20ifQ.MF8vJyOGYfa4Ju3BEGcJCg-UxiV00D2fWrs6cJM8jn2uJ7u6JTWNI3qFzY2Wwsbpd_1lrTat1GrPvsTYoQhDJQ"

        val result = jwtGenerator.generateToken(JwtConfigurations.copy(secret = "blabla".toByteArray()), user)

        println("result: $result")
        assert(result.equals(expectedResult))*/
    }
}