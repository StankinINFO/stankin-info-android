package visapps.mystankin.app.info.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import visapps.mystankin.app.di.ViewModelKey
import visapps.mystankin.app.info.home.InfoViewModel

@Module
abstract class InfoViewModelModule {
//    @Binds
//    @IntoMap
//    @ViewModelKey(InfoViewModel::class)
//    abstract fun bindInfoViewModel(infoViewModel: InfoViewModel): ViewModel
}