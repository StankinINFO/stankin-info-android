package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.NewsItem
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.repository.NewsRepository

class NewItemUseCase (private val newsRepository: NewsRepository){
    operator fun invoke(id:Int): Observable<Result<NewsItem>> {
        return newsRepository
            .getItemNews(id)
            .subscribeOn(Schedulers.io())
            .map { Result.Success(it) as Result<NewsItem> }
            .doOnError{it.printStackTrace()}
            .startWith ( Result.Loading)
            .onErrorReturn {Result.Error(it)}
    }
}