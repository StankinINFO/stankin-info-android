package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.domain.model.NetworkState
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.NewsQuery
import visapps.mystankin.domain.repository.NewsRepository

class NewsUseCase(private val newsRepository: NewsRepository) {

    val networkState = BehaviorSubject.create<NetworkState>()

    operator fun invoke(query: NewsQuery): Observable<List<News>> {
        return newsRepository
            .getNews(query)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { networkState.onNext(NetworkState.RUNNING) }
            .doOnNext { networkState.onNext(NetworkState.SUCCESS) }
            .doOnError {
                it.printStackTrace()
                networkState.onNext(NetworkState.FAILED) }
            .onErrorReturn { emptyList() }
    }
}
