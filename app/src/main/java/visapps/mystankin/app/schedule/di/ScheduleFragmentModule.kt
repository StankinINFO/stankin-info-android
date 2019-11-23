package visapps.mystankin.app.schedule.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import visapps.mystankin.app.schedule.home.ScheduleFragment

@Module
abstract class ScheduleFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeScheduleFragment(): ScheduleFragment
}