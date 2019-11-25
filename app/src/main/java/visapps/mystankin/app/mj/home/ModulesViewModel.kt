package visapps.mystankin.app.mj.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.Semester
import visapps.mystankin.domain.model.SubjectWithMarks
import visapps.mystankin.domain.model.User
import visapps.mystankin.domain.usecase.SubjectsWithMarksUseCase
import javax.inject.Inject

class ModulesViewModel @Inject constructor(val subjectsWithMarksUseCase: SubjectsWithMarksUseCase)
    : StankinViewModel() {

    private val user = BehaviorSubject.create<User>()
    private val semester = BehaviorSubject.create<Semester>()
    val marks: LiveData<Result<List<SubjectWithMarks>>>

    init {
        val query: Observable<Pair<User, Semester>> = Observable.combineLatest(user, semester, BiFunction { u, s -> Pair(u, s) })
        marks = LiveDataReactiveStreams.fromPublisher(query.switchMap {
            subjectsWithMarksUseCase(it.first.student, it.second.name)}.toFlowable(BackpressureStrategy.LATEST))

    }

    fun changeSemester(semester: Semester) {
        this.semester.onNext(semester)
    }

    fun changeUser(user: User) {
        this.user.onNext(user)
    }
}
