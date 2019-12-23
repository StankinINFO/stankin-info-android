package visapps.mystankin.data.news.repository

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.usecase.NewsUseCase

class NewsPagedDataSourceFactory(private val is_main:Boolean,private val subdivisionId: Int,
                                 private val useCase: NewsUseCase,
                                 private val disposable: CompositeDisposable
): DataSource.Factory<Int, News>() {

    var dataSource: NewsPagedDataSource? = null

    override fun create(): DataSource<Int, News>
            = NewsPagedDataSource(is_main,subdivisionId, useCase, disposable).also { dataSource = it }
}