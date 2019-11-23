package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester

interface MJRepository {

    fun getMarks(semester: Semester) : Observable<List<Mark>>

    fun getSemesters() : Observable<List<Semester>>
}