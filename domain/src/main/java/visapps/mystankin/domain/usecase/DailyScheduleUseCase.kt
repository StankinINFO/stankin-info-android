package visapps.mystankin.domain.usecase

import visapps.mystankin.domain.repository.ScheduleRepository
import javax.inject.Inject

class DailyScheduleUseCase @Inject constructor(private val scheduleRepository: ScheduleRepository) {

    val selectedGroup = scheduleRepository.getSelectedGroup()
}