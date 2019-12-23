package visapps.mystankin.domain.model

sealed class AuthState {
    data class Authenticated(val user: User) : AuthState()
    object NotAuthenticated: AuthState()
}