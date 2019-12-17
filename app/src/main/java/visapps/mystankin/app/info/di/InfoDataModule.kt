package visapps.mystankin.app.info.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import visapps.mystankin.data.StankinDb
import visapps.mystankin.data.schedule.api.ScheduleService
import visapps.mystankin.data.schedule.database.ScheduleDao
import visapps.mystankin.data.schedule.repository.ScheduleRepositoryImpl
import visapps.mystankin.domain.repository.ScheduleRepository
import javax.inject.Singleton

@Module
class InfoDataModule {

}