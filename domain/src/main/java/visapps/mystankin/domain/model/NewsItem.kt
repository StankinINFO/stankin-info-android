package visapps.mystankin.domain.model

import java.util.*

class NewsItem(val id:Int?,
               val date: Date?,
               val title:String?,
               val short_text: String?,
               val logo: String?,
               val tags: String?,
               val text:String?,
               val author_id:Int?,
               val attachments: String?,
               val subdivision_id: Int?,
               val pull_site : Boolean?,
               val is_main: Boolean?,
               val delta:String)