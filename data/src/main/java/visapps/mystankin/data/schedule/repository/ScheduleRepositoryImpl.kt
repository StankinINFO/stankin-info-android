package visapps.mystankin.data.schedule.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import visapps.mystankin.data.schedule.api.ScheduleService
import visapps.mystankin.data.schedule.database.ScheduleDao
import visapps.mystankin.domain.model.SelectedGroup
import visapps.mystankin.domain.model.ScheduleItem
import visapps.mystankin.domain.model.StudentGroup
import visapps.mystankin.domain.repository.ScheduleRepository
import java.text.SimpleDateFormat
import java.util.*

class ScheduleRepositoryImpl(private val service: ScheduleService,
                             private val dao: ScheduleDao,
                             private val preferences: SharedPreferences,
                             private val gson: Gson) : ScheduleRepository {

    private val selectedGroup = BehaviorSubject.createDefault(parseSelectedGroup())

    private val prefChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if(key == SELECTED_GROUP_KEY) {
            selectedGroup.onNext(parseSelectedGroup())
        }
    }

    init {
        preferences.registerOnSharedPreferenceChangeListener(prefChangeListener)
    }

    override fun getStudentGroups(query: String): Observable<List<StudentGroup>>
            = service.getStudentGroups(query).map { it.results }

    override fun getDailySchedule(
        classId: Int,
        subclass: Int,
        date: Date
    ): Observable<List<ScheduleItem>>
            = service.getDailySchedule(classId, subclass, SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date)).map { it.results }

    override fun getSelectedGroup(): Observable<SelectedGroup> = selectedGroup.distinctUntilChanged()

    override fun saveSelectedGroup(group: SelectedGroup) { preferences.edit().putString(SELECTED_GROUP_KEY, gson.toJson(group)).apply()}

    private fun parseSelectedGroup(): SelectedGroup = try{
        gson.fromJson(preferences.getString(SELECTED_GROUP_KEY, "") ?: "", SelectedGroup::class.java)
    } catch(e: Exception){
        SelectedGroup.EMPTY
    }

    companion object {
        private const val SELECTED_GROUP_KEY = "SELECTED_GROUP"
    }

}