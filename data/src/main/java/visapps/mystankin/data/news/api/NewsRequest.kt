package visapps.mystankin.data.news.api

class NewsRequest(val is_main: Boolean = true,
                  val pull_site: Boolean = true,
                  val subdivision_id: Int,
                  val count: Int,
                  val page: Int,
                  val tag: String,
                  val query_search: String)