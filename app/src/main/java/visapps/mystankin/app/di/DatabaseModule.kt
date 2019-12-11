package visapps.mystankin.app.di

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

}