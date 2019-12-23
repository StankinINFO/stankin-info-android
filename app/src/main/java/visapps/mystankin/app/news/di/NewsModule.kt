package visapps.mystankin.app.news.di

import dagger.Module

@Module(includes = [NewsDataModule::class, NewsFragmentsModule::class, NewsViewModelModule::class])
interface NewsModule