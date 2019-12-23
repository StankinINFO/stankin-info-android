package visapps.mystankin.app.schedule.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.app.R
import visapps.mystankin.app.StankinApplication
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.ScheduleItem
import visapps.mystankin.domain.model.SelectedGroup
import visapps.mystankin.domain.usecase.DailyScheduleUseCase
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(val app: StankinApplication, val dailyScheduleUseCase: DailyScheduleUseCase): StankinViewModel() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    private val selectedDate = BehaviorSubject.createDefault(Date())

    private val selectedGroup = MutableLiveData<SelectedGroup>()

    val schedule = MutableLiveData<Result<List<ScheduleItem>>>()

    val groupSelectedState = map(selectedGroup) {!it.isEmpty}

    val group = map(selectedGroup) {
        if(it.isEmpty) {app.getString(R.string.schedule)} else {"${it.group} (${it.subClassTitle})"}
    }

    val date = MutableLiveData<String>()

    init {
        compositeDisposable.add(Observable.combineLatest<Date, SelectedGroup, String>(
            selectedDate, dailyScheduleUseCase.selectedGroup, BiFunction{date, group ->
                if(group.isEmpty) {""} else { SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date)}
            }).subscribe {
            date.postValue(it)
        })
        compositeDisposable.add(dailyScheduleUseCase.selectedGroup
            .subscribe {
                selectedGroup.postValue(it)
            })
        compositeDisposable.add(dailyScheduleUseCase(selectedDate)
            .subscribeOn(Schedulers.io())
            .subscribe {
                schedule.postValue(it)
            })
    }

    fun changeDate(year: Int, month: Int, dayOfMonth: Int) {
        val date = dateFormat.parse("$year-${month+1}-$dayOfMonth") ?: Date()
        selectedDate.onNext(date)
    }

    fun forward() {
        addDays(1)
    }

    fun backward() {
        addDays(-1)
    }

    fun reload() {
        selectedDate.onNext(selectedDate.value ?: Date())
    }

    fun selectedDate(): Date = selectedDate.value ?: Date()

    private fun addDays(amount: Int) {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate.value ?: Date()
        calendar.add(Calendar.DATE, amount)
        selectedDate.onNext(calendar.time)
    }
}
