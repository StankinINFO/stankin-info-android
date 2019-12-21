package visapps.mystankin.domain.repository

import io.reactivex.Completable
import io.reactivex.Observable
import visapps.mystankin.domain.model.SelectedGroup
import visapps.mystankin.domain.model.ScheduleItem
import visapps.mystankin.domain.model.StudentGroup
import java.util.*

interface ScheduleRepository {

    fun getStudentGroups(query: String): Observable<List<StudentGroup>>

    fun getDailySchedule(classId: Int, subclass: Int, date: Date): Observable<List<ScheduleItem>>

    fun getSelectedGroup(): Observable<SelectedGroup>

    fun saveSelectedGroup(group: SelectedGroup): Completable
}