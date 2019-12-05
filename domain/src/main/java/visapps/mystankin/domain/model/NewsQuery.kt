package visapps.mystankin.domain.model

class NewsQuery(val subdivisionId: Int,
                val count: Int,
                val page: Int,
                val tag: String = "",
                val querySearch: String = "")