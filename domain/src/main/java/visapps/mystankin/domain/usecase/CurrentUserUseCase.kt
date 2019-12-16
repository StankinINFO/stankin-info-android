package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import visapps.mystankin.domain.model.AuthState
import visapps.mystankin.domain.repository.MJUserRepository

class CurrentUserUseCase(private val userRepository: MJUserRepository) {

    operator fun invoke(): Observable<AuthState> {
        return userRepository
            .getCurrentUser()
            .map { AuthState.Authenticated(it) as AuthState}
            .onErrorReturn { AuthState.NotAuthenticated }
    }
}