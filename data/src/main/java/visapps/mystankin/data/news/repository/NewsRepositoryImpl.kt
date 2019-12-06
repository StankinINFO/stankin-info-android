package visapps.mystankin.data.news.repository

import visapps.mystankin.domain.repository.NewsRepository
import io.reactivex.Observable
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.NewsQuery

class NewsRepositoryImpl(val remote: NewsRemoteDataSource, val local: NewsLocalDataSource) :
    NewsRepository {
    override fun getNews(query: NewsQuery): Observable<List<News>> {
        return remote.getNews(query)
    }
}