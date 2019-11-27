package visapps.mystankin.app.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import visapps.mystankin.app.StankinApplication
import visapps.mystankin.app.mj.di.MJModule
import visapps.mystankin.app.news.di.NewsModule
import visapps.mystankin.app.schedule.di.ScheduleModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ScheduleModule::class,
        MJModule::class,
        NewsModule::class]
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