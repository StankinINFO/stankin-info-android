package visapps.mystankin.data.news

import visapps.mystankin.domain.repository.NewsRepository
import io.reactivex.Observable
import visapps.mystankin.domain.model.ShortNews
import visapps.mystankin.domain.model.NewsQuery

class NewsRepositoryImpl(val remote: NewsRemoteDataSource, val local: NewsLocalDataSource) :
    NewsRepository {
    override fun getNews(query: NewsQuery): Observable<List<ShortNews>> {
        return remote.getNews(query)
    }
}