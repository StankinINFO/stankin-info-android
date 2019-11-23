package visapps.mystankin.data.api

import android.database.Observable
import retrofit2.http.POST
import visapps.mystankin.domain.model.Mark

interface MJService {

    @POST("")
    fun getMarks() : Observable<List<Mark>>

    companion object {
        const val ENDPOINT = "https://lk.stankin.ru/api2/"
    }
}