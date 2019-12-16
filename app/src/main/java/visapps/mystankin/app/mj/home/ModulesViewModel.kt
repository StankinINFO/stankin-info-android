package visapps.mystankin.app.mj.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.*
import visapps.mystankin.domain.usecase.CurrentUserUseCase
import visapps.mystankin.domain.usecase.SubjectsWithMarksUseCase
import javax.inject.Inject

class ModulesViewModel @Inject constructor(val currentUserUseCase: CurrentUserUseCase, val subjectsWithMarksUseCase: SubjectsWithMarksUseCase)
    : StankinViewModel() {

    private val user = BehaviorSubject.create<User>()
    private val semester = BehaviorSubject.create<String>()
    val marks: LiveData<Result<List<SubjectWithMarks>>>
    val authState: LiveData<AuthState> = LiveDataReactiveStreams.fromPublisher(currentUserUseCase().toFlowable(BackpressureStrategy.LATEST))

    init {
        val query: Observable<Pair<User, String>> = Observable.combineLatest(user, semester, BiFunction { u, s -> Pair(u, s) })
        marks = LiveDataReactiveStreams.fromPublisher(query.switchMap {
            subjectsWithMarksUseCase(it.first.student, it.second)}.toFlowable(BackpressureStrategy.LATEST))

    }

    fun changeSemester(semester: String) {
        this.semester.onNext(semester)
    }

    fun changeUser(user: User) {
        this.user.onNext(user)
    }
}
