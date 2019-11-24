package visapps.mystankin.app.mj.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester
import visapps.mystankin.domain.usecase.SubjectsWithMarksUseCase
import javax.inject.Inject

class ModulesViewModel @Inject constructor(val subjectsWithMarksUseCase: SubjectsWithMarksUseCase)
    : StankinViewModel() {

    fun loadSemesters(student:String,password:String,semester:String) {
        // здесь получаем из UseCase и преобразуем в LiveData
        val marks = MutableLiveData<List<Mark>>()
        compositeDisposable.add(subjectsWithMarksUseCase(student,password,semester)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {result-> return@subscribe })
    }
}
