package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.SubjectWithMarks
import visapps.mystankin.domain.repository.MJRepository

class SubjectsWithMarksUseCase (private val mjRepository: MJRepository) {

    operator fun invoke(student:String, semester:String): Observable<Result<List<SubjectWithMarks>>> {
        return mjRepository
            .getMarks(student, semester)
            .subscribeOn(Schedulers.io())
            .map { marks -> marks.groupBy { it.title }.map { SubjectWithMarks.fromMarks(it.value) }.sortedBy { it.subject } }
            .map { Result.Success(it) as Result<List<SubjectWithMarks>>}
            .startWith ( Result.Loading)
            .onErrorReturn { Result.Error(it) }
    }

}