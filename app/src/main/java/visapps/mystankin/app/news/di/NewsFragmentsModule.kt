package visapps.mystankin.app.news.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import visapps.mystankin.app.news.home.NewsHostFragment
import visapps.mystankin.app.news.home.NewsFragment

@Module
abstract class NewsFragmentsModule {

    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsHostFragment
    @ContributesAndroidInjector
    abstract fun contributeViewPagerFragment(): NewsFragment
}