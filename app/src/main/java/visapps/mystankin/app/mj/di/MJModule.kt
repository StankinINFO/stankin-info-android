package visapps.mystankin.app.mj.di

import dagger.Module

@Module(includes = [MJDataModule::class, MJFragmentsModule::class, MJViewModelModule::class])
interface MJModule