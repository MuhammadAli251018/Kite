package online.muhammadali.kite.common.di

import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.Application
import online.muhammadali.kite.auth.data.repositories.MongoUsersRepo
import online.muhammadali.kite.auth.data.source.UsersDb
import online.muhammadali.kite.auth.data.util.security.JwtGenerator
import online.muhammadali.kite.auth.domain.models.TokenConfig
import online.muhammadali.kite.common.utl.getVariableOrThrow
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.time.Duration.Companion.minutes
import kotlin.time.times

/*
fun Application.dataModule(): Module
{
    return module {
        single {
            val connectionString = getVariableOrThrow("ktor.db.connection")
                UsersDb(
                    MongoClient.create(connectionString).getDatabase("users")
                )
        }

        single { MongoUsersRepo(get()) }

        single {TokenConfig(
            issuer = getVariableOrThrow("jwt.domain"),
            audience = "jwt.audience",
            expiresDuration = (30 * 24 * 60.minutes).inWholeMilliseconds,
            secret = getVariableOrThrow("jwt.key").encodeToByteArray()
        )  }

        factory { JwtGenerator() }
    }
}*/
