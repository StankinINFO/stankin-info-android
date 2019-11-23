package visapps.mystankin.app.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import visapps.mystankin.app.MainActivity
import visapps.mystankin.app.mj.di.MJFragmentsModule
import visapps.mystankin.app.util.StankinViewModelFactory

@Module
abstract class ApplicationModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindViewModelFactory(factory: StankinViewModelFactory): ViewModelProvider.Factory
}