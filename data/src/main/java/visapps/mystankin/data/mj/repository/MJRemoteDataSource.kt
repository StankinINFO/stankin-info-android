package visapps.mystankin.data.mj.repository

import io.reactivex.Observable
import visapps.mystankin.data.mj.api.MJService
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester

class MJRemoteDataSource(val service: MJService) {

    //здесь получаем данные из сети

    fun getMarks(student:String,password:String,semester:String): Observable<List<Mark>> {
        return service.getMarks(student,password,semester)

    }

    fun getSemesters(): Observable<List<Semester>> {
        return Observable.just(emptyList())
    }
}