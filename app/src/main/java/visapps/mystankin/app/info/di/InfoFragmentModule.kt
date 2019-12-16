package visapps.mystankin.app.info.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import visapps.mystankin.app.info.home.InfoFragment


@Module
abstract class InfoFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeInfoFragment(): InfoFragment
}