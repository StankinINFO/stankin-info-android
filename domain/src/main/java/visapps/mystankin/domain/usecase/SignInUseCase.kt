package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.repository.MJUserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(val repository: MJUserRepository) {

    operator fun invoke(credentials: Observable<Pair<String, String>>): Observable<Result<Unit>> {
        return credentials.switchMap {
            repository.signIn(it.first, it.second)
                .subscribeOn(Schedulers.io())
                .andThen(Observable.just(Result.Success(Unit) as Result<Unit>))
                .startWith(Result.Loading)
                .onErrorReturn { e -> Result.Error(e) }
        }
    }
}