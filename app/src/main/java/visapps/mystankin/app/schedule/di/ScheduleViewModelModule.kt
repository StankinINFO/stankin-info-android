package visapps.mystankin.app.schedule.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import visapps.mystankin.app.di.ViewModelKey
import visapps.mystankin.app.schedule.home.ScheduleViewModel
import visapps.mystankin.app.schedule.selectGroup.SelectGroupViewModel
import visapps.mystankin.app.util.StankinViewModelFactory

@Module
abstract class ScheduleViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ScheduleViewModel::class)
    abstract fun bindScheduleViewModel(scheduleViewModel: ScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectGroupViewModel::class)
    abstract fun bindSelectGroupViewModel(selectGroupViewModel: SelectGroupViewModel): ViewModel
}