package visapps.mystankin.app.news.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations.map
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.NewsItem
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.usecase.NewItemUseCase
import javax.inject.Inject

class NewsItemViewModel @Inject constructor(val useCase: NewItemUseCase): StankinViewModel() {
    val id = BehaviorSubject.create<Int>()
    var news: LiveData<Result<NewsItem>> = LiveDataReactiveStreams.fromPublisher(id.switchMap { useCase(it) }.toFlowable(BackpressureStrategy.LATEST))
    val title = map(news){if(it is Result.Success){it.data.title}else{ "test"} }
    val date = map(news){if(it is Result.Success){it.data.date}else{ "test"} }

    fun setup(id:Int) {
        this.id.onNext(id)

    }
}
