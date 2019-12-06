package visapps.mystankin.data.news.repository

import io.reactivex.Observable
import visapps.mystankin.data.news.database.NewsDao

class NewsLocalDataSource(val dao: NewsDao) {

    fun getNews(): Observable<String> {
        return Observable.just("")
    }

}