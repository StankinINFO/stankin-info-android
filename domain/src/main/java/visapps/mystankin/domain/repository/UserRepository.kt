package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.User
import visapps.mystankin.domain.model.Result

interface UserRepository {

    fun signIn(student: String, password: String): Observable<Result<User>>

    fun getCurrentUser() : Observable<User>

    fun getUsers(): Observable<List<User>>

    fun changeUser(student: String)

    fun logOut(student: String)

}