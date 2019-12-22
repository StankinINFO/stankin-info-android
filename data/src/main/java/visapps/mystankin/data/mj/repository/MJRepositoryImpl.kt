package visapps.mystankin.data.mj.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.data.mj.repository.MJLocalDataSource
import visapps.mystankin.data.mj.repository.MJRemoteDataSource
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester
import visapps.mystankin.domain.repository.MJRepository

class MJRepositoryImpl(val remote: MJRemoteDataSource,
                       val local: MJLocalDataSource,
                       val accounts: MJAccountDataSource
) : MJRepository {

    override fun getMarks(student:String,semester:String): Observable<List<Mark>> {
        return accounts.getPassword(student).flatMapObservable { remote.getMarks(student, it, semester) }.subscribeOn(Schedulers.io())

    }

    override fun getSemesters(): Observable<List<Semester>> {
        return Observable.just(emptyList())
    }
}