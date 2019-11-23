package visapps.mystankin.app.schedule.home

import visapps.mystankin.app.base.StankinViewModel
import visapps.mystankin.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(val repository: ScheduleRepository): StankinViewModel() {

    init{
        println(repository.javaClass.toString())
    }
}
