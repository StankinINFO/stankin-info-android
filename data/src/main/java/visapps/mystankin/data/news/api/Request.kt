package visapps.mystankin.data.news.api

import visapps.mystankin.domain.model.NewsQuery

class Request<T>(val action: String, val data: T) {

    companion object {

        fun createNewsRequest(query: NewsQuery) =
            Request("getNews", NewsRequest(
                subdivision_id = query.subdivisionId,
                count = query.count,
                page = query.page,
                tag = query.tag,
                query_search = query.querySearch))
    }
}