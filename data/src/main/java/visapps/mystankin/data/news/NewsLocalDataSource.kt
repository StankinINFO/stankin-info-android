package visapps.mystankin.data.news

import io.reactivex.Observable
import visapps.mystankin.data.database.NewsDao

class NewsLocalDataSource(val dao:NewsDao) {

    fun getNews(): Observable<String> {
        return Observable.just("")
    }

}