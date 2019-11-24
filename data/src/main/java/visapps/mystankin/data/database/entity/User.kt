package visapps.mystankin.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val student: String,
    val password:String,
    val group:String,
    val surname:String,
    val initials:String

)