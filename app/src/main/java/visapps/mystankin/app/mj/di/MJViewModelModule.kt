package visapps.mystankin.app.mj.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import visapps.mystankin.app.di.ViewModelKey
import visapps.mystankin.app.mj.home.ModulesViewModel
import visapps.mystankin.app.mj.login.LoginViewModel

@Module
abstract class MJViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ModulesViewModel::class)
    abstract fun bindModulesViewModel(modulesViewModel: ModulesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}