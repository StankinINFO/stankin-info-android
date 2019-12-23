package visapps.mystankin.app.info.di

import dagger.Module

@Module(includes = [InfoDataModule::class, InfoFragmentModule::class, InfoViewModelModule::class])
interface InfoModule