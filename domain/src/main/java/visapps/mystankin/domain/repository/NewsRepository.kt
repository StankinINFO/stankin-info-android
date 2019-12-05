package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.ShortNews
import visapps.mystankin.domain.model.NewsQuery

interface NewsRepository {
    fun getNews(query: NewsQuery) : Observable<List<ShortNews>>
}