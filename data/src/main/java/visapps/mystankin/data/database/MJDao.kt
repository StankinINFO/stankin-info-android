package visapps.mystankin.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import visapps.mystankin.data.database.entity.Marks
import visapps.mystankin.data.database.entity.Semesters
import visapps.mystankin.data.database.entity.Users

@Dao
interface MJDao {
    @Insert
    fun insertMarks(marks: Marks?)
    @Insert
    fun insertUser(users: Users?)
    @Update
    fun updateMarks(marks: Marks?)
    @Update
    fun updateUser(users: Users?)
    @Insert
    fun insertSemester(semesters: Semesters)
    @Update
    fun updateSemester(semesters: Semesters)
}