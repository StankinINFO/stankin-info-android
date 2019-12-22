package visapps.mystankin.app.mj.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.*
import visapps.mystankin.domain.usecase.CurrentUserUseCase
import visapps.mystankin.domain.usecase.SubjectsWithMarksUseCase
import javax.inject.Inject

class ModulesViewModel @Inject constructor(val currentUserUseCase: CurrentUserUseCase, val subjectsWithMarksUseCase: SubjectsWithMarksUseCase)
    : StankinViewModel() {

    private val semester = BehaviorSubject.create<String>()

    val marks = MutableLiveData<Result<List<SubjectWithMarks>>>()
    val authState = MutableLiveData<AuthState>()

    init {
        compositeDisposable.add(currentUserUseCase().subscribe {
            authState.postValue(it)
        })
        compositeDisposable.add(subjectsWithMarksUseCase(semester)
            .subscribeOn(Schedulers.io())
            .subscribe {
            marks.postValue(it)
        })
    }

    fun changeSemester(semester: String) {
        this.semester.onNext(semester)
    }

    fun exit() {
        compositeDisposable.add(currentUserUseCase.logOut().subscribe {  })
    }
}
