package visapps.mystankin.app.schedule.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.app.R
import visapps.mystankin.app.StankinApplication
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.SelectedGroup
import visapps.mystankin.domain.repository.ScheduleRepository
import visapps.mystankin.domain.usecase.DailyScheduleUseCase
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(val app: StankinApplication, val dailyScheduleUseCase: DailyScheduleUseCase): StankinViewModel() {

    private val selectedDate = BehaviorSubject.createDefault(Date())

    private val selectedGroup = LiveDataReactiveStreams.fromPublisher(dailyScheduleUseCase.selectedGroup.toFlowable(BackpressureStrategy.LATEST))

    val groupSelectedState = map(selectedGroup) {!it.isEmpty}

    val group = map(selectedGroup) {
        if(it.isEmpty) {app.getString(R.string.schedule)} else {"${it.group} (${it.subClassTitle})"}
    }

    val date = LiveDataReactiveStreams.fromPublisher(Observable.combineLatest<Date, SelectedGroup, String>(
        selectedDate, dailyScheduleUseCase.selectedGroup, BiFunction{date, group ->
            if(group.isEmpty) {""} else { SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date)}
        }).toFlowable(BackpressureStrategy.LATEST))
}
