package visapps.mystankin.data.mj.repository

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountsException
import android.content.Context
import android.os.Bundle
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.data.util.CryptoStorage
import visapps.mystankin.domain.model.User
import kotlin.Exception

class MJAccountDataSource(context: Context, val cryptoStorage: CryptoStorage) {

    private val accountManager: AccountManager = AccountManager.get(context)

    val accounts = BehaviorSubject.create<List<String>>()

    init {
        updateAccounts(accountManager.getAccountsByType(MJ_ACCOUNT_TYPE).asList())
        accountManager.addOnAccountsUpdatedListener({ accounts-> updateAccounts(accounts.filter { it.type == MJ_ACCOUNT_TYPE }) }, null, true)
    }

    fun addUser(user: User, password: String): Completable =
        cryptoStorage.encrypt(password).flatMapCompletable { encryptedPassword->
            Completable.create {
                try{
                    val result = accountManager.addAccountExplicitly(Account(user.student, MJ_ACCOUNT_TYPE), encryptedPassword, Bundle())
                    if(result){
                        it.onComplete()
                    }
                    else{
                        it.onError(AccountsException())
                    }
                }
                catch(e: Exception) {
                    e.printStackTrace()
                    it.onError(e)
                }
        } }

    fun getUser(student: String): Single<User> = getUserAccount(student).map { account->
        val surname = accountManager.getUserData(account, SURNAME) ?: ""
        val initials = accountManager.getUserData(account, INITIALS) ?: ""
        val stgroup = accountManager.getUserData(account, STGROUP) ?: ""
        User(student, surname, initials, stgroup)
    }

    fun getPassword(student: String): Single<String> = getUserAccount(student)
        .flatMap { getAccountPassword(it) }
        .flatMap { cryptoStorage.decrypt(it) }

    fun removeUser(student: String): Completable = getUserAccount(student)
        .flatMapCompletable { account->
            Completable.create {
                accountManager.removeAccount(account, { future->
                    if(future.result){
                        it.onComplete()
                    }
                    else {
                        it.onError(AccountsException())
                    }
                }, null)
            }
        }

    private fun getUserAccount(student: String): Single<Account> = Single.create {
        val result = accountManager.getAccountsByType(MJ_ACCOUNT_TYPE)
            .filter { it.type == MJ_ACCOUNT_TYPE }.find { it.name == student }
        result?.let { account->
            it.onSuccess(account)
        } ?: run {
            it.onError(AccountsException())
        }
    }

    private fun getAccountPassword(account: Account): Single<String> = Single.create {
        try{
            val password = accountManager.getPassword(account)
            it.onSuccess(password)
        }
        catch(e: Exception) {
            it.onError(AccountsException())
        }
    }

    private fun updateAccounts(newAccounts: List<Account>) {
        accounts.onNext(newAccounts.map { it.name })
    }

    companion object {
        const val SURNAME = "surname"
        const val INITIALS = "initials"
        const val STGROUP = "stgroup"
        const val MJ_ACCOUNT_TYPE = "stankin.info.app"
    }
}