package visapps.mystankin.domain.model

import java.util.*

class NewsItem(val id:Int,
               val title:String,
               val date: Date,
               val logo: String,
               val tags: String,
               val short_text: String,
               val author_id: Int)