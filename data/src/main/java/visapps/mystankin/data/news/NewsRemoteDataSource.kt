package visapps.mystankin.data.news

import io.reactivex.Observable
import visapps.mystankin.data.api.NewsService
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.News

class NewsRemoteDataSource(val service:NewsService) {
    fun getNews(): Observable<List<News>> {
        return service.getAllNews()

    }
}