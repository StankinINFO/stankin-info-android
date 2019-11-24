package visapps.mystankin.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Semesters::class,parentColumns = ["id"],childColumns = ["id"]),ForeignKey(entity = Semesters::class,parentColumns = ["semester"],childColumns = ["semester"])])
class Marks (
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