package online.muhammadali.kite

import SERVER_PORT
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import online.muhammadali.kite.plugins.configureAuthentication
import online.muhammadali.kite.plugins.configureDi
import online.muhammadali.kite.plugins.configureRouting
import online.muhammadali.kite.plugins.configureSerialization

fun main( args: Array<String>): Unit = EngineMain.main(args)/* {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}*/

fun Application.module() {
    println(environment.config.propertyOrNull("jwt.domain"))
    configureDi()
    configureAuthentication()
    configureSerialization()
    configureRouting()
}