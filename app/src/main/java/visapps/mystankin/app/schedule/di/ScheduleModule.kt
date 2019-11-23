package visapps.mystankin.app.schedule.di

import dagger.Module

@Module(includes = [ScheduleDataModule::class, ScheduleFragmentModule::class, ScheduleViewModelModule::class])
interface ScheduleModule