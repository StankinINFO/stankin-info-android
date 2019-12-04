package visapps.mystankin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import visapps.mystankin.data.database.entity.Mark
import visapps.mystankin.data.database.entity.Semester
import visapps.mystankin.data.database.entity.User

//@Database(entities = [User::class, Mark::class,Semester::class,News::class,MoreNews::class], version = 1, exportSchema = false)
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class StankinDb : RoomDatabase() {

    abstract fun mjDao(): MJDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun newsDao(): NewsDao

    companion object {
        const val DATABASE_NAME = "stankin.db"
    }

}