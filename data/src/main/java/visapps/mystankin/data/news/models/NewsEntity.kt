package visapps.mystankin.data.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import visapps.mystankin.data.news.util.NewsDateAdapter
import visapps.mystankin.domain.model.News
import java.util.*

@Entity
class NewsEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    @JsonAdapter(NewsDateAdapter::class)
    val date: Date,
    val logo: String,
    @SerializedName("short_text")
    val shortText: String?) {

    fun toNews(): News =
        News(id, title, date, logo, shortText ?: "")
}