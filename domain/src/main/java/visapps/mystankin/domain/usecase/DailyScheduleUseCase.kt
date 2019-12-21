package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.ScheduleItem
import visapps.mystankin.domain.model.SelectedGroup
import visapps.mystankin.domain.repository.ScheduleRepository
import java.util.*
import javax.inject.Inject

class DailyScheduleUseCase @Inject constructor(private val scheduleRepository: ScheduleRepository) {

    val selectedGroup = scheduleRepository.getSelectedGroup()

    operator fun invoke(date: Observable<Date>): Observable<Result<List<ScheduleItem>>> {
        return Observable.combineLatest<SelectedGroup, Date, Pair<SelectedGroup, Date>>(selectedGroup, date, BiFunction { g, d -> Pair(g, d) })
            .switchMap { scheduleRepository.
                getDailySchedule(it.first.id, it.first.subClass, it.second).
                subscribeOn(Schedulers.io())
                .map { Result.Success(it) as Result<List<ScheduleItem>>}
                .startWith ( Result.Loading)
                .onErrorReturn { Result.Error(it) }
            }
    }
}