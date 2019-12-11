package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.NewsQuery

interface NewsRepository {
    fun getNews(query: NewsQuery) : Observable<List<News>>
}