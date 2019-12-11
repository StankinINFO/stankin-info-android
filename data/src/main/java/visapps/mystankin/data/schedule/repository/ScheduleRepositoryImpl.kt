package visapps.mystankin.data.schedule.repository

import visapps.mystankin.data.schedule.api.ScheduleService
import visapps.mystankin.data.schedule.database.ScheduleDao
import visapps.mystankin.domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(val service: ScheduleService, val dao: ScheduleDao) : ScheduleRepository {
}