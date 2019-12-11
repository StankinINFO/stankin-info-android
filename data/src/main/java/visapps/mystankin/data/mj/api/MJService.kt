package visapps.mystankin.data.mj.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import visapps.mystankin.domain.model.Mark
import visapps.mystankin.domain.model.Semester

interface MJService {

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @POST("marks")
    fun getMarks(@Field("student") cardId:String, @Field("password") password:String, @Field("semester") semester:String) : Observable<List<Mark>>

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @POST("semesters")
    fun getSemesters(@Field("student") cardId:String, @Field("password") password:String) : Observable<List<Semester>>

    companion object {
        const val ENDPOINT = "https://lk.stankin.ru/webapi/api2/"
    }
}