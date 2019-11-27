package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.News

interface NewsRepository {
    fun getNews() : Observable<List<News>>
}