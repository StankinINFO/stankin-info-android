package visapps.mystankin.data.mj

import io.reactivex.Completable
import io.reactivex.Observable
import visapps.mystankin.data.database.MJDao
import visapps.mystankin.data.database.entity.Mark
import visapps.mystankin.data.database.entity.Semester
import visapps.mystankin.data.database.entity.User

class MJLocalDataSource(val dao: MJDao) {

    //здесь мы можем получить или сохранить данные в БД

    fun getUserPassword(): Observable<String> {
        return Observable.just("")
    }

//    fun getMarks(semester:String,id:Int): Observable<List<Mark>> {
//        dao.getMarks(id,semester)
//        return Observable.just(emptyList())
//    }
//
//    fun getSemesters(id:Int): Observable<List<Semester>> {
//        dao.getSemesters(id)
//        return Observable.just(emptyList())
//    }
//    fun getUsers(): Observable<List<Semester>> {
//        return Observable.just(emptyList())
//    }
//
//    fun saveMarks(mark: Mark): Completable {
//        dao.insertMarks(mark)
//        return Completable.complete()
//    }
//
//    fun saveSemesters(semester: Semester): Completable {
//        dao.insertSemester(semester)
//        return Completable.complete()
//    }
//    fun saveUsers(user: User): Completable {
//        dao.insertUser(user)
//        return Completable.complete()
//    }
//    fun updateMarks(mark: Mark): Completable {
//        dao.updateMarks(mark)
//        return Completable.complete()
//    }
//
//
//    fun updateUsersPassword(id:Int,password:String):Completable{
//        dao.updateUserPassword(id,password)
//        return Completable.complete()
//    }

}