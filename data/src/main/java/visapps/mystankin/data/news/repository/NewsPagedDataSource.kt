package visapps.mystankin.data.news.repository

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import visapps.mystankin.domain.model.NewsQuery
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.usecase.NewsUseCase

class NewsPagedDataSource(val is_main:Boolean,val subdivisionId: Int,
                          val useCase: NewsUseCase,
                          val disposable: CompositeDisposable): PageKeyedDataSource<Int, News>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, News>
    ) {
        val query = NewsQuery(subdivisionId = subdivisionId, count = params.requestedLoadSize, page = 1,is_main = is_main)
        disposable.add(useCase(query).subscribe { callback.onResult(it, null,2) })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        val query = NewsQuery(subdivisionId = subdivisionId, count = params.requestedLoadSize, page = params.key,is_main = is_main)
        disposable.add(useCase(query).subscribe { callback.onResult(it, params.key + 1) })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }
}