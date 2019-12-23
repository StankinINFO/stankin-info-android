package visapps.mystankin.app.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import visapps.mystankin.app.StankinApplication
import visapps.mystankin.data.StankinDb
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: StankinApplication): StankinDb =
        Room.databaseBuilder(application, StankinDb::class.java, StankinDb.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideSharedPreferences(application: StankinApplication): SharedPreferences
            = application.getSharedPreferences("StankinINFO", Context.MODE_PRIVATE)

}