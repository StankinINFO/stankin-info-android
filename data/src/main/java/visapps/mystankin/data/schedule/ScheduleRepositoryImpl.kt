package visapps.mystankin.data.schedule

import visapps.mystankin.data.api.ScheduleService
import visapps.mystankin.data.database.ScheduleDao
import visapps.mystankin.domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(val service: ScheduleService, val dao: ScheduleDao) : ScheduleRepository {
}