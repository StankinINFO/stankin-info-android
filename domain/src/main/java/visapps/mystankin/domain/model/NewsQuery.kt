package visapps.mystankin.domain.model

class NewsQuery(val is_main:Boolean,
                val subdivisionId: Int,
                val count: Int,
                val page: Int,
                val tag: String = "",
                val querySearch: String = "")