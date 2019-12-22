package visapps.mystankin.data.schedule.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import visapps.mystankin.domain.model.ScheduleItem
import visapps.mystankin.domain.model.StudentGroup

interface ScheduleService {

    @GET("university/classes")
    fun getStudentGroups(@Query("q") query: String): Observable<Response<StudentGroup>>

    @GET("schedule/class/daily")
    fun getDailySchedule(@Query("classId") classId: Int,
                         @Query("subclass") subclass: Int,
                         @Query("date") date: String) : Observable<Response<ScheduleItem>>

    companion object {
        const val ENDPOINT = "https://stankinschedule.herokuapp.com/"
    }
}