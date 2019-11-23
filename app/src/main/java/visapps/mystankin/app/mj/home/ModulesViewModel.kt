package visapps.mystankin.app.mj.home

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.Semester
import visapps.mystankin.domain.usecase.SubjectsWithMarksUseCase
import javax.inject.Inject

class ModulesViewModel @Inject constructor(val subjectsWithMarksUseCase: SubjectsWithMarksUseCase)
    : StankinViewModel() {

    fun loadSemesters() {
        // здесь получаем из UseCase и преобразуем в LiveData
        compositeDisposable.add(subjectsWithMarksUseCase(Semester())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println("need to map it to livedata")
            })
    }
}
