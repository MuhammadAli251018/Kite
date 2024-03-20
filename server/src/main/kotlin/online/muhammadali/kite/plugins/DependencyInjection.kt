package online.muhammadali.kite.plugins

import io.ktor.server.application.Application
import online.muhammadali.kite.auth.di.authModule
import org.koin.ktor.plugin.koin

fun Application.configureDi() {
    koin {
        modules(authModule())
    }
}