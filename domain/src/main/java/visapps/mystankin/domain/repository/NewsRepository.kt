package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.NewsItem
import visapps.mystankin.domain.model.NewsQuery

interface NewsRepository {
    fun getNews(query: NewsQuery) : Observable<List<News>>
    fun getItemNews(id:Int) :Observable<NewsItem>
}