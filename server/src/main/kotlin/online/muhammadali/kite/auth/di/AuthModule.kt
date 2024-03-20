package online.muhammadali.kite.auth.di

import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.Application
import online.muhammadali.kite.auth.data.repositories.MongoUsersRepo
import online.muhammadali.kite.auth.data.source.UsersDb
import online.muhammadali.kite.auth.data.util.security.JwtGenerator
import online.muhammadali.kite.auth.domain.models.TokenConfig
import online.muhammadali.kite.auth.domain.repositories.UsersDBRepo
import online.muhammadali.kite.auth.presentation.routes.token.ClientTokenVerifier
import online.muhammadali.kite.auth.presentation.routes.token.GoogleTokenVerifier
import online.muhammadali.kite.auth.presentation.routes.token.TokenRequestViewModel
import online.muhammadali.kite.auth.presentation.routes.token.TokenRequestVmImp
import online.muhammadali.kite.common.utl.getVariableOrThrow
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.time.Duration.Companion.minutes
import kotlin.time.times

fun Application.authModule(): Module {
    return module {
        factory<ClientTokenVerifier>{ GoogleTokenVerifier(
            getVariableOrThrow("auth.google.issuer"),
            getVariableOrThrow("auth.google.audience")
        ) }

        single {
            TokenConfig(
                issuer = getVariableOrThrow("jwt.domain"),
                audience = "jwt.audience",
                expiresDuration = (30 * 24 * 60.minutes).inWholeMilliseconds,
                secret = getVariableOrThrow("jwt.key").encodeToByteArray()
            )  }

        single {
            val connectionString = getVariableOrThrow("db.connection")
            UsersDb(
                MongoClient.create(connectionString).getDatabase("users")
            )
        }

        single<UsersDBRepo> { MongoUsersRepo(get()) }

        factory { JwtGenerator() }

        factory<TokenRequestViewModel> { TokenRequestVmImp(
            usersDBRepo = get(),
            tokenGenerator = get(),
            verifier = get(),
            jwtConfig = get()
            ) }
    }
}