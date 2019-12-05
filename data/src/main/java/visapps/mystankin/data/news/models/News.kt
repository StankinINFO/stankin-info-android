package visapps.mystankin.data.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.JsonAdapter
import visapps.mystankin.data.news.util.NewsDateAdapter
import visapps.mystankin.domain.model.ShortNews
import java.util.*

@Entity
class News(
    @PrimaryKey
    val id: Int,
    val title: String,
    @JsonAdapter(NewsDateAdapter::class)
    val date: Date,
    val logo: String) {

    fun toShortNews(): ShortNews = ShortNews(id, title, date, logo)
}