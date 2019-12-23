package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester

interface MJRepository {

    fun getMarks(student:String,semester:String) : Observable<List<Mark>>


    fun getSemesters(student:String) : Observable<List<String>>
}
