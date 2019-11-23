package visapps.mystankin.app.mj.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import visapps.mystankin.app.di.ViewModelKey
import visapps.mystankin.app.mj.home.ModulesViewModel

@Module
abstract class MJViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ModulesViewModel::class)
    abstract fun bindUserViewModel(modulesViewModel: ModulesViewModel): ViewModel
}