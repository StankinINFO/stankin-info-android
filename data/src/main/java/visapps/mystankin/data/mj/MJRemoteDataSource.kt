package visapps.mystankin.data.mj

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.data.api.MJService
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