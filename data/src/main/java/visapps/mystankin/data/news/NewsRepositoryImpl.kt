package visapps.mystankin.data.news

import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.repository.NewsRepository
import io.reactivex.Observable
import visapps.mystankin.domain.model.JustNews
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.QuaryForNews

class NewsRepositoryImpl ( val remote: NewsRemoteDataSource, val local: NewsLocalDataSource) :
    NewsRepository {
    override fun getNews(quary: QuaryForNews): Observable<List<JustNews>> {
        return local.getNews().flatMap { remote.getNews(quary) }.subscribeOn(Schedulers.io())
    }
}