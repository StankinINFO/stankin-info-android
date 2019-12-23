package visapps.mystankin.app.mj.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.app.util.SingleLiveEvent
import visapps.mystankin.domain.model.AuthState
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.usecase.CurrentUserUseCase
import visapps.mystankin.domain.usecase.SignInUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(val currentUserUseCase: CurrentUserUseCase, singInUseCase: SignInUseCase): StankinViewModel () {

    private val credentials = PublishSubject.create<Pair<String, String>>()

    enum class LoginState{
        NOT_AUTHENTICATED,
        LOADING,
        SUCCESS
    }

    val authState = MutableLiveData<AuthState>()
    val loginState = MutableLiveData<LoginState>()
    val error = SingleLiveEvent<String>()

    init {
        loginState.postValue(LoginState.NOT_AUTHENTICATED)
        compositeDisposable.add(currentUserUseCase().subscribe {
            authState.postValue(it)
        })
        compositeDisposable.add(singInUseCase(credentials)
            .subscribeOn(Schedulers.io())
            .subscribe {
                when(it) {
                    is Result.Success -> loginState.postValue(LoginState.SUCCESS)
                    is Result.Loading -> loginState.postValue(LoginState.LOADING)
                    is Result.Error -> {
                        loginState.postValue(LoginState.NOT_AUTHENTICATED)
                        error.postValue(it.throwable.message)
                    }
                }
            })
    }

    fun login(student: String, password: String) {
        credentials.onNext(Pair(student, password))
    }
}