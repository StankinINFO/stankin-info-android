package visapps.mystankin.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Semester::class,parentColumns = ["id"],childColumns = ["id"]),ForeignKey(entity = Semester::class,parentColumns = ["semester"],childColumns = ["semester"])])
class Mark (
    @PrimaryKey
    val id: Int,
    val semester: String,
    val subject: String,
    val moduleFirst: String,
    val moduleSecond: String,
    val course: String,
    val offset: String,
    val exam: String,
    val coefficient: String
)