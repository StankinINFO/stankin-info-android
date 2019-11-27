package visapps.mystankin.app.news.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import visapps.mystankin.app.di.ViewModelKey
import visapps.mystankin.app.news.home.NewsHostViewModel

@Module
abstract class NewsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsHostViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsHostViewModel): ViewModel
}