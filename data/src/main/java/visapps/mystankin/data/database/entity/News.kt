package visapps.mystankin.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class News (
    @PrimaryKey
    val id:Int,
    val title:String,
    val date: Date,
    val logo:String,
    val tags:String,
    val short_text:String,
    val author_id:Int
)