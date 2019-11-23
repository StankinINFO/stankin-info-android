package visapps.mystankin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import visapps.mystankin.data.database.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class StankinDb : RoomDatabase() {

    abstract fun mjDao(): MJDao

    companion object {
        const val DATABASE_NAME = "stankin.db"
    }

}