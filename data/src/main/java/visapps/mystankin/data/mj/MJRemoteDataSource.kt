package visapps.mystankin.data.mj

import io.reactivex.Observable
import visapps.mystankin.data.api.MJService
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester

class MJRemoteDataSource(val service: MJService) {

    //здесь получаем данные из сети, сразу возвращаем и сохраняем в БД

    fun getMarks(semester: Semester): Observable<List<Mark>> {
        return Observable.just(emptyList())
    }

    fun getSemesters(): Observable<List<Semester>> {
        return Observable.just(emptyList())
    }
}