package visapps.mystankin.data.mj.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.data.mj.repository.MJLocalDataSource
import visapps.mystankin.data.mj.repository.MJRemoteDataSource
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester
import visapps.mystankin.domain.repository.MJRepository

class MJRepositoryImpl(val remote: MJRemoteDataSource,
                       val local: MJLocalDataSource
) : MJRepository {

    override fun getMarks(student:String,semester:String): Observable<List<Mark>> {
        // тут соединяем из бд и сети, сохраняем в бд если получили из сети
        return local.getUserPassword().flatMap { remote.getMarks(student, it, semester) }.subscribeOn(Schedulers.io())

    }

    override fun getSemesters(): Observable<List<Semester>> {
        // тут соединяем из бд и сети, сохраняем в бд если получили из сети
        return Observable.just(emptyList())
    }
}