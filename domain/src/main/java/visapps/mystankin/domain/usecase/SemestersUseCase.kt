package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.User
import visapps.mystankin.domain.repository.MJRepository
import visapps.mystankin.domain.repository.MJUserRepository
import javax.inject.Inject

class SemestersUseCase @Inject constructor(private val mjUserRepository: MJUserRepository, private val mjRepository: MJRepository) {

    operator fun invoke(refreshTrigger: Observable<Unit>): Observable<Result<List<String>>> {
        return Observable.combineLatest<User, Unit, User>(
            mjUserRepository.getCurrentUser().filter { !it.isEmpty },
            refreshTrigger,
            BiFunction { u, r -> u })
            .switchMap { u ->
                mjRepository
                    .getSemesters(u.student)
                    .subscribeOn(Schedulers.io())
                    .map { Result.Success(it) as Result<List<String>> }
                    .startWith ( Result.Loading)
                    .onErrorReturn { Result.Error(it) }
            }
    }
}