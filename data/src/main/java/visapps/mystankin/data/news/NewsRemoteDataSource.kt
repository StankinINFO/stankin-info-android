package visapps.mystankin.data.news

import io.reactivex.Observable
import visapps.mystankin.data.news.api.NewsService
import visapps.mystankin.data.news.api.Request
import visapps.mystankin.domain.model.*

class NewsRemoteDataSource(val service: NewsService) {
    fun getNews(query: NewsQuery): Observable<List<ShortNews>> {
        return service.getNews(Request.createNewsRequest(query)).map { it.data.news.map { item -> item.toShortNews() } }
    }
}