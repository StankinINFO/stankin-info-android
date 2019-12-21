package visapps.mystankin.data.news.repository

import visapps.mystankin.domain.repository.NewsRepository
import io.reactivex.Observable
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.NewsItem
import visapps.mystankin.domain.model.NewsQuery

class NewsRepositoryImpl(val remote: NewsRemoteDataSource, val local: NewsLocalDataSource) :
    NewsRepository {
    override fun getNews(query: NewsQuery): Observable<List<News>> {
        return remote.getNews(query)
    }

    override fun getItemNews(id: Int): Observable<NewsItem> {
        return remote.getItemNews(id).doOnNext{println(it.delta)}
    }
}