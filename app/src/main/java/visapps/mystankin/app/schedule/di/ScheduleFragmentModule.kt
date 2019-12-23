package visapps.mystankin.app.schedule.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import visapps.mystankin.app.schedule.home.ScheduleFragment
import visapps.mystankin.app.schedule.selectGroup.SelectGroupFragment

@Module
abstract class ScheduleFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeScheduleFragment(): ScheduleFragment

    @ContributesAndroidInjector
    abstract fun contributeSelectGroupFragment(): SelectGroupFragment
}