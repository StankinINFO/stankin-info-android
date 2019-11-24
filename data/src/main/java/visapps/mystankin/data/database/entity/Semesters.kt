package visapps.mystankin.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Users::class,parentColumns = ["id"],childColumns = ["id"])])
class Semesters (
    @PrimaryKey
    val id:Int,
    val semester:String
)