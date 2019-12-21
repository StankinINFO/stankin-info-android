package visapps.mystankin.domain.usecase

import io.reactivex.Completable
import io.reactivex.Observable
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.SelectedGroup
import visapps.mystankin.domain.model.StudentGroup
import visapps.mystankin.domain.repository.ScheduleRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchGroupsUseCase @Inject constructor(private val scheduleRepository: ScheduleRepository) {

    operator fun invoke(query: Observable<String>): Observable<Result<List<StudentGroup>>> {
        return query.
            debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { scheduleRepository.getStudentGroups(it) }
            .map { Result.Success(it) as Result<List<StudentGroup>>}
            .startWith (Result.Loading)
            .onErrorReturn { Result.Error(it) }
    }

    fun saveSelectedGroup(group: StudentGroup, subClass: Int)
            = scheduleRepository.saveSelectedGroup(SelectedGroup(group.id, group.name, subClass))
}