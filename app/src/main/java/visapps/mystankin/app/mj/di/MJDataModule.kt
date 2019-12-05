package visapps.mystankin.app.mj.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import visapps.mystankin.data.mj.api.MJService
import visapps.mystankin.data.mj.database.MJDao
import visapps.mystankin.data.StankinDb
import visapps.mystankin.data.mj.repository.MJLocalDataSource
import visapps.mystankin.data.mj.repository.MJRemoteDataSource
import visapps.mystankin.data.mj.repository.MJRepositoryImpl
import visapps.mystankin.domain.repository.MJRepository
import visapps.mystankin.domain.usecase.SubjectsWithMarksUseCase
import javax.inject.Singleton

@Module
class MJDataModule {

    @Provides
    @Singleton
    fun provideSubjectsWithMarksUseCase(mjRepository: MJRepository): SubjectsWithMarksUseCase
            = SubjectsWithMarksUseCase(mjRepository)

    @Provides
    @Singleton
    fun provideMJRepository(remote: MJRemoteDataSource, local: MJLocalDataSource): MJRepository
            =
        MJRepositoryImpl(remote, local)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: MJDao): MJLocalDataSource =
        MJLocalDataSource(dao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: MJService): MJRemoteDataSource =
        MJRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideMJDao(db: StankinDb): MJDao = db.mjDao()

    @Provides
    @Singleton
    fun provideMJService(
        client: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): MJService {
        return Retrofit.Builder()
            .baseUrl(MJService.ENDPOINT)
            .client(client)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
            .create(MJService::class.java)
    }
}