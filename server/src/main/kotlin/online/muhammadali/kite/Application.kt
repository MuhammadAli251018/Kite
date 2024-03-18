package online.muhammadali.kite

import io.ktor.server.application.*
import io.ktor.server.netty.*
import online.muhammadali.kite.plugins.configureAuthentication
import online.muhammadali.kite.plugins.configureDi
import online.muhammadali.kite.plugins.configureRouting
import online.muhammadali.kite.plugins.configureSerialization

fun main( args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    println(environment.config.propertyOrNull("jwt.domain")?.getString())
    configureDi()
    configureAuthentication()
    configureSerialization()
    configureRouting()
}