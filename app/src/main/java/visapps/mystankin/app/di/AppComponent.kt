package visapps.mystankin.app.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import visapps.mystankin.app.StankinApplication
import visapps.mystankin.app.mj.di.MJDataModule
import visapps.mystankin.app.mj.di.MJViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        MJDataModule::class,
        MJViewModelModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(stankinApplication: StankinApplication): Builder

        fun build(): AppComponent
    }

    fun inject(stankinApplication: StankinApplication)
}