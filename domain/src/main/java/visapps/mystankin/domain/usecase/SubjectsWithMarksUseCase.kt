package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.SubjectWithMarks
import visapps.mystankin.domain.model.User
import visapps.mystankin.domain.repository.MJRepository
import visapps.mystankin.domain.repository.MJUserRepository
import javax.inject.Inject

class SubjectsWithMarksUseCase @Inject constructor(private val mjUserRepository: MJUserRepository, private val mjRepository: MJRepository) {

    operator fun invoke(semester: Observable<String>): Observable<Result<List<SubjectWithMarks>>> {
        return Observable.combineLatest<User, String, Pair<User, String>>(
            mjUserRepository.getCurrentUser().filter { !it.isEmpty },
            semester,
            BiFunction { u, s -> Pair(u, s) })
            .switchMap { query->
                mjRepository
                    .getMarks(query.first.student, query.second)
                    .subscribeOn(Schedulers.io())
                    .doOnError { it.printStackTrace() }
                    .map { marks -> marks.groupBy { it.title }.map { SubjectWithMarks.fromMarks(it.value) }.sortedBy { it.subject } }
                    .map { Result.Success(it) as Result<List<SubjectWithMarks>>}
                    .startWith ( Result.Loading)
                    .onErrorReturn { Result.Error(it) }
        }
    }

}