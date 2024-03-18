package online.muhammadali.kite.common.domain

open class Route internal constructor (val path: String) {

    private fun subRoute(extensionalPath: String) = Route("$path$extensionalPath")

    val root =  Route("/")
    val auth = root.subRoute("/auth")
    val registerNewUser = auth.subRoute("/register-user")
    val authenticateUser = auth.subRoute("/authenticate-user")

    // todo add other routes
}
