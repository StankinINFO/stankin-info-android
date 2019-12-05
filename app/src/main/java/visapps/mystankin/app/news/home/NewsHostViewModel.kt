package visapps.mystankin.app.news.home

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.NewsQuery
import visapps.mystankin.domain.repository.NewsRepository
import visapps.mystankin.domain.usecase.NewsUseCase
import javax.inject.Inject

class NewsHostViewModel @Inject constructor(val newsRepository: NewsRepository)
    : StankinViewModel() {
    val test = MutableLiveData<String>()
    val errora = MutableLiveData<String>()
    fun loadSemesters(query: NewsQuery) {
        // здесь получаем из UseCase и преобразуем в LiveData
        compositeDisposable.add(newsRepository.getNews(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ result -> test.postValue(result[0].title)
                println(result[0].title) },
                { error -> errora.postValue(error.message) }))

    }
    fun getNews(query: NewsQuery){
        newsRepository.getNews(query)
    }
}