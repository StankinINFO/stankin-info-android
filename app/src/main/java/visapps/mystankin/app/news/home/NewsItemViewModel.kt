package visapps.mystankin.app.news.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.model.NewsItem
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.usecase.NewItemUseCase
import javax.inject.Inject

class NewsItemViewModel @Inject constructor(val useCase: NewItemUseCase): StankinViewModel() {

    val news = MutableLiveData<Result<NewsItem>> ()
    val title = map(news){if(it is Result.Success){it.data.title}else{ "" } }

    fun setup(id:Int) {
        compositeDisposable.add(useCase(id).subscribe {
            news.postValue(it)
        })
    }
}
