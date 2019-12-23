package visapps.mystankin.app.news.home

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.BackpressureStrategy
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.data.news.repository.NewsPagedDataSourceFactory
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.usecase.NewsUseCase
import javax.inject.Inject

class NewsViewModel @Inject constructor(val useCase: NewsUseCase)
    : StankinViewModel() {
    val test = MutableLiveData<String>()
    val errora = MutableLiveData<String>()
    var newsList: LiveData<PagedList<News>>?=null
    lateinit var factory:NewsPagedDataSourceFactory
    val networkState = LiveDataReactiveStreams.fromPublisher(useCase.networkState.toFlowable(BackpressureStrategy.LATEST))
    var isMain:Boolean = true
    var subdivision = 125

    fun setup(is_main: Boolean, subDivision: Int) {
        isMain= is_main
        subdivision= subDivision

        factory = NewsPagedDataSourceFactory(isMain,subdivision, useCase, compositeDisposable)
        if (newsList==null)
        {
            val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()
           newsList = LivePagedListBuilder<Int, News>(factory, config).build()
        }
    }


}