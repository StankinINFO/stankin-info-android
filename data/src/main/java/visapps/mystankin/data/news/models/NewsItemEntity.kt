package visapps.mystankin.data.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import com.google.gson.annotations.JsonAdapter
import visapps.mystankin.data.news.util.NewsDateAdapter
import visapps.mystankin.domain.model.NewsItem
import java.util.*

@Entity
class NewsItemEntity(
    @PrimaryKey
    val id:Int,
    @JsonAdapter(NewsDateAdapter::class)
    val date: Date,
    val title:String,
    val short_text: String,
    val logo: String,
    val tags: String,
    val text:String,
    val author_id:Int,
    val attachments: String,
    val subdivision_id: Int,
    val pull_site : Boolean,
    val is_main: Boolean,
    val delta:JsonObject)
{
    fun toNews(): NewsItem =
        NewsItem(id, date,title,short_text, logo, tags, text, author_id, attachments, subdivision_id, pull_site, is_main,delta.toString())
}