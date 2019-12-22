package visapps.mystankin.data.mj.repository

import android.accounts.AccountsException
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import visapps.mystankin.domain.model.User
import visapps.mystankin.domain.repository.MJUserRepository

class MJUserRepositoryImpl(val remoteDataSource: MJRemoteDataSource, val accountDataSource: MJAccountDataSource): MJUserRepository {

    override fun signIn(student: String, password: String): Completable {
        return remoteDataSource.getSemesters(student, password)
            .map { it.toUser(student)}
            .flatMapCompletable { accountDataSource.addUser(it, password)}
    }

    override fun getCurrentUser(): Observable<User> {
        return accountDataSource.accounts.switchMap{accounts->
            accounts.firstOrNull()?.let {
                accountDataSource.getUser(it).toObservable().onErrorReturn{User.EMPTY}
            } ?: Observable.just(User.EMPTY)
        }
    }

    override fun getUsers(): Observable<List<String>> {
        return accountDataSource.accounts
    }

    override fun logOut(student: String): Completable = accountDataSource.removeUser(student)
}