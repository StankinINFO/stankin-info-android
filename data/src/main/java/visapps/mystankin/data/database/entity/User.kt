package visapps.mystankin.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (
    @PrimaryKey
    val student: String
)