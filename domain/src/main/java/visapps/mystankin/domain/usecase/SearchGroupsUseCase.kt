package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.SelectedGroup
import visapps.mystankin.domain.model.StudentGroup
import visapps.mystankin.domain.repository.ScheduleRepository
import javax.inject.Inject

class SearchGroupsUseCase @Inject constructor(private val scheduleRepository: ScheduleRepository) {

    private val refreshTrigger = BehaviorSubject.createDefault(Unit)

    operator fun invoke(query: Observable<String>): Observable<Result<List<StudentGroup>>> {
        return Observable.combineLatest<Result<List<StudentGroup>>, String, Result<List<StudentGroup>>>(
            refreshTrigger.switchMap {
                scheduleRepository.getStudentGroups("")
                    .subscribeOn( Schedulers.io())
                    .map { Result.Success(it) as Result<List<StudentGroup>>}
                    .startWith (Result.Loading)
                    .onErrorReturn { Result.Error(it) }
            },
            query,
            BiFunction { result, q -> if(result is Result.Success) { Result.Success(result.data.filter { it.name.contains(q, true) }) } else {result} })
    }

    fun refresh() {
        refreshTrigger.onNext(Unit)
    }

    fun saveSelectedGroup(group: StudentGroup, subClass: Int)
            = scheduleRepository.saveSelectedGroup(SelectedGroup(group.id, group.name, subClass))
}