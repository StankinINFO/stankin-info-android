package visapps.mystankin.data.news

import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.repository.NewsRepository
import io.reactivex.Observable
import visapps.mystankin.domain.model.News

class NewsRepositoryImpl ( val remote: NewsRemoteDataSource, val local: NewsLocalDataSource) :
    NewsRepository {
    override fun getNews(): Observable<List<News>> {
        return local.getNews().flatMap { remote.getNews() }.subscribeOn(Schedulers.io())
    }
}