package visapps.mystankin.app.mj.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.*
import visapps.mystankin.domain.usecase.CurrentUserUseCase
import visapps.mystankin.domain.usecase.SemestersUseCase
import visapps.mystankin.domain.usecase.SubjectsWithMarksUseCase
import javax.inject.Inject

class ModulesViewModel @Inject constructor(val currentUserUseCase: CurrentUserUseCase,
                                           val semestersUseCase: SemestersUseCase,
                                           val subjectsWithMarksUseCase: SubjectsWithMarksUseCase)
    : StankinViewModel() {

    private val refreshTrigger = PublishSubject.create<Unit>()

    private val semester = BehaviorSubject.create<String>()

    val marks = MutableLiveData<Result<List<SubjectWithMarks>>>()
    val semesters = MutableLiveData<Result<List<String>>>()
    val authState = MutableLiveData<AuthState>()

    init {
        compositeDisposable.add((semestersUseCase(refreshTrigger)
            .subscribe {
            semesters.postValue(it)
        }))
        compositeDisposable.add(currentUserUseCase().subscribe {
            authState.postValue(it)
        })
        compositeDisposable.add(subjectsWithMarksUseCase(semester)
            .subscribeOn(Schedulers.io())
            .subscribe {
            marks.postValue(it)
        })
        refreshTrigger.onNext(Unit)
    }

    fun changeSemester(semester: String) {
        this.semester.onNext(semester)
    }

    fun exit() {
        compositeDisposable.add(currentUserUseCase.logOut().subscribe {  })
    }
}
