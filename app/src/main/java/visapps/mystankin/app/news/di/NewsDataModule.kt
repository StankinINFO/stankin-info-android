package visapps.mystankin.app.news.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import visapps.mystankin.data.news.api.NewsService
import visapps.mystankin.data.news.database.NewsDao
import visapps.mystankin.data.StankinDb
import visapps.mystankin.data.news.repository.NewsLocalDataSource
import visapps.mystankin.data.news.repository.NewsRemoteDataSource
import visapps.mystankin.data.news.repository.NewsRepositoryImpl
import visapps.mystankin.domain.repository.NewsRepository
import visapps.mystankin.domain.usecase.NewItemUseCase
import visapps.mystankin.domain.usecase.NewsUseCase
import javax.inject.Singleton

@Module
class NewsDataModule {

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase
            = NewsUseCase(newsRepository)
    @Provides
    @Singleton
    fun provideNewsItemUseCase(newsRepository: NewsRepository): NewItemUseCase
            = NewItemUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideNewsRepository(remote: NewsRemoteDataSource, local: NewsLocalDataSource): NewsRepository
            =
        NewsRepositoryImpl(remote, local)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: NewsDao): NewsLocalDataSource =
        NewsLocalDataSource(dao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: NewsService): NewsRemoteDataSource =
        NewsRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideNewsDao(db: StankinDb): NewsDao = db.newsDao()

    @Provides
    @Singleton
    fun provideNewsService(
        client: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): NewsService {
        return Retrofit.Builder()
            .baseUrl(NewsService.ENDPOINT)
            .client(client)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
            .create(NewsService::class.java)
    }
}