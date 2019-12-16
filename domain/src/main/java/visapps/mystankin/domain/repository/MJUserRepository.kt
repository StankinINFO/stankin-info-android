package visapps.mystankin.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import visapps.mystankin.domain.model.User

interface MJUserRepository {

    fun signIn(student: String, password: CharArray): Completable

    fun getCurrentUser(): Observable<User?>

    fun logOut(student: String): Completable

}