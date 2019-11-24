package visapps.mystankin.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import visapps.mystankin.data.database.entity.Mark
import visapps.mystankin.data.database.entity.Semester
import visapps.mystankin.data.database.entity.User

@Dao
interface MJDao {
    @Insert
    fun insertMarks(mark: Mark?)
    @Insert
    fun insertUser(user: User?)
    @Update
    fun updateMarks(mark: Mark?)

    @Insert
    fun insertSemester(semester: Semester)

//    @Query("SELECT semester FROM Semester WHERE id=:id")
//    fun getSemesters(id:Int): LiveData<List<Semester>>
//    @Query("SELECT * FROM Mark WHERE id=:id and semester=:semesters")
//    fun getMarks(id:Int,semesters:String): LiveData<List<Mark>>
//    @Query("UPDATE users SET password=:password WHERE id=:idUsers")
//    fun updateUserPassword(idUsers:Int,password:String)
}