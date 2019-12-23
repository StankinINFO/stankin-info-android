package visapps.mystankin.app.schedule.di

import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import visapps.mystankin.data.schedule.api.ScheduleService
import visapps.mystankin.data.schedule.database.ScheduleDao
import visapps.mystankin.data.StankinDb
import visapps.mystankin.data.schedule.repository.ScheduleRepositoryImpl
import visapps.mystankin.domain.repository.ScheduleRepository
import javax.inject.Singleton

@Module
class ScheduleDataModule {

    @Provides
    @Singleton
    fun provideScheduleRepository(service: ScheduleService,
                                  dao: ScheduleDao,
                                  sharedPreferences: SharedPreferences,
                                  gson: Gson): ScheduleRepository
            = ScheduleRepositoryImpl(service, dao, sharedPreferences, gson)

    @Provides
    @Singleton
    fun provideScheduleDao(db: StankinDb): ScheduleDao = db.scheduleDao()

    @Provides
    @Singleton
    fun provideScheduleService(
        client: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory
    ): ScheduleService {
        return Retrofit.Builder()
            .baseUrl(ScheduleService.ENDPOINT)
            .client(client)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
            .create(ScheduleService::class.java)
    }
}