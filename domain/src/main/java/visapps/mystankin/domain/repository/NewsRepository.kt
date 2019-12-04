package visapps.mystankin.domain.repository

import io.reactivex.Observable
import visapps.mystankin.domain.model.JustNews
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.QuaryForNews

interface NewsRepository {
    fun getNews(quary: QuaryForNews) : Observable<List<JustNews>>
}