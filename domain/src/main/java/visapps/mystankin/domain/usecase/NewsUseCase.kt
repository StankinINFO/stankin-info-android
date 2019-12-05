package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.ShortNews
import visapps.mystankin.domain.model.NewsQuery
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.repository.NewsRepository

class NewsUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(query: NewsQuery): Observable<Result<List<ShortNews>>> {
        return newsRepository
            .getNews(query)
            .subscribeOn(Schedulers.io())
            // .map { marks -> marks.groupBy { it.title }.map { NewsNews.fromMarks(it.value) }.sortedBy { it.id } }
            .map {  Result.Success(it) as Result<List<ShortNews>> }
    }
}
