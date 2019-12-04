package visapps.mystankin.domain.usecase

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.domain.model.JustNews
import visapps.mystankin.domain.model.QuaryForNews
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.repository.NewsRepository

class NewsUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(quary: QuaryForNews): Observable<Result<List<JustNews>>> {
        return newsRepository
            .getNews(quary)
            .subscribeOn(Schedulers.io())
            // .map { marks -> marks.groupBy { it.title }.map { NewsNews.fromMarks(it.value) }.sortedBy { it.id } }
            .map {  Result.Success(it) as Result<List<JustNews>> }
    }
}
