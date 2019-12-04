package visapps.mystankin.data.news

import io.reactivex.Observable
import visapps.mystankin.data.api.NewsService
import visapps.mystankin.domain.model.*

class NewsRemoteDataSource(val service:NewsService) {
    fun getNews(quary: QuaryForNews): Observable<List<JustNews>> {
        return service.getAllNews(quary)
    }
}