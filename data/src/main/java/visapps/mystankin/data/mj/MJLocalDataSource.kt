package visapps.mystankin.data.mj

import io.reactivex.Completable
import io.reactivex.Observable
import visapps.mystankin.data.database.MJDao
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester

class MJLocalDataSource(val dao: MJDao) {

    //здесь мы можем получить или сохранить данные в БД

    fun getMarks(semester: Semester): Observable<List<Mark>> {
        return Observable.just(emptyList())
    }

    fun getSemesters(): Observable<List<Semester>> {
        return Observable.just(emptyList())
    }

    fun saveMarks(semester: Semester): Completable {
        return Completable.complete()
    }

    fun saveSemesters(): Completable {
        return Completable.complete()
    }
}