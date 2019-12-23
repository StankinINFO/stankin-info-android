package visapps.mystankin.domain.usecase

import io.reactivex.Completable
import io.reactivex.Observable
import visapps.mystankin.domain.model.AuthState
import visapps.mystankin.domain.repository.MJUserRepository

class CurrentUserUseCase(private val userRepository: MJUserRepository) {

    operator fun invoke(): Observable<AuthState> {
        return userRepository
            .getCurrentUser()
            .map {
                if(!it.isEmpty) {AuthState.Authenticated(it)} else { AuthState.NotAuthenticated}
            }
    }

    fun logOut(): Completable = userRepository.getCurrentUser().firstOrError().flatMapCompletable { userRepository.logOut(it.student) }
}