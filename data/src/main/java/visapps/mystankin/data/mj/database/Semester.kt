package visapps.mystankin.data.mj.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import visapps.mystankin.data.mj.database.User

@Entity(foreignKeys = [ForeignKey(entity = User::class,parentColumns = ["id"],childColumns = ["id"])])
class Semester (
    @PrimaryKey
    val id:Int,
    val semester:String
)