package visapps.mystankin.app.news.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.BackpressureStrategy
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.data.news.repository.NewsPagedDataSourceFactory
import visapps.mystankin.domain.model.NewsQuery
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.usecase.NewsUseCase
import javax.inject.Inject

class NewsHostViewModel @Inject constructor(val useCase: NewsUseCase)
    : StankinViewModel() {
    val test = MutableLiveData<String>()
    val errora = MutableLiveData<String>()
    val newsList: LiveData<PagedList<News>>
    val networkState = LiveDataReactiveStreams.fromPublisher(useCase.networkState.toFlowable(BackpressureStrategy.LATEST))

    private val factory = NewsPagedDataSourceFactory(0, useCase, compositeDisposable)

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder<Int, News>(factory, config).build()
    }
}