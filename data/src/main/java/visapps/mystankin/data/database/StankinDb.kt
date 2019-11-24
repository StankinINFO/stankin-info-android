package visapps.mystankin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import visapps.mystankin.data.database.entity.Marks
import visapps.mystankin.data.database.entity.Semesters
import visapps.mystankin.data.database.entity.Users

@Database(entities = [Users::class, Marks::class,Semesters::class], version = 1, exportSchema = false)
abstract class StankinDb : RoomDatabase() {

    abstract fun mjDao(): MJDao
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        const val DATABASE_NAME = "stankin.db"
    }

}