package visapps.mystankin.app.schedule.selectGroup

import androidx.lifecycle.MutableLiveData
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.StudentGroup
import visapps.mystankin.domain.usecase.SearchGroupsUseCase
import javax.inject.Inject

class SelectGroupViewModel @Inject constructor(private val useCase: SearchGroupsUseCase): StankinViewModel() {

    private val querySubject = BehaviorSubject.createDefault("")

    val groups = MutableLiveData<Result<List<StudentGroup>>>()

    init {
        compositeDisposable.add(useCase(querySubject)
            .subscribeOn(Schedulers.io())
            .subscribe {
                groups.postValue(it)
            })
    }

    fun search(query: String) {
        querySubject.onNext(query)
    }

    fun reload() {
        useCase.refresh()
    }

    fun selectGroup(group: StudentGroup, subClass: Int) {
        useCase.saveSelectedGroup(group, subClass)
    }

}