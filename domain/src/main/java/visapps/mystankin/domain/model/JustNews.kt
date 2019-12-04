package visapps.mystankin.domain.model

import java.util.*

class JustNews(val id:Int, val title:String, val date: Date, val logo:String)
{
    companion object Factory{
        fun fromMarks(news: List<News>): JustNews {
            val id = news.first().id
            val title = news.first().title
            val date = news.first().date
            val logo = news.first().logo

            return JustNews(id, title,date,logo)
        }
    }
}