package online.muhammadali.kite.common.di

import online.muhammadali.kite.auth.data.util.security.JwtGenerator
import online.muhammadali.kite.auth.presentation.routes.token.ClientTokenVerifier
import online.muhammadali.kite.auth.presentation.routes.token.GoogleTokenVerifier
import online.muhammadali.kite.auth.presentation.routes.token.TokenRequestViewModel
import online.muhammadali.kite.auth.presentation.routes.token.TokenRequestVmImp

object PresentationModule {
    val tokenRequestViewModel: TokenRequestViewModel
        get() = TokenRequestVmImp(
            usersDBRepo = DatabaseModule.usersDBRepo,
            verifier = GoogleTokenVerifier(),
            tokenGenerator = JwtGenerator()
        )
}