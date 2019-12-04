package visapps.mystankin.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import visapps.mystankin.domain.model.DataNews
import java.util.*

@Entity
class MoreNews (
    @PrimaryKey
    val id:Int,
    val date:Date,
    val title:String,
    val short_text:String,
    val logo:String,
    val tags:String,
    val text:String,
    val author_id:Int,
    val attachments:Boolean,
    val subdivision_id:Int,
    val pull_site: Boolean,
    val is_main: Boolean,
    val delta:List<DataNews>)