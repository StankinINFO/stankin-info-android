package visapps.mystankin.data.api

import io.reactivex.Observable
import retrofit2.http.*
import visapps.mystankin.domain.model.JustNews
import visapps.mystankin.domain.model.News
import visapps.mystankin.domain.model.QuaryForNews

interface NewsService {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("api_entry.php?grantType=value")
    fun getAllNews(@Body body: QuaryForNews) : Observable<List<JustNews>>
    @FormUrlEncoded
    @Headers("Content-Type:application/json")
    @POST("api_entry.php")
    fun getNewsById(@Body body:List<QuaryForNews>) : Observable<List<News>>
    @FormUrlEncoded
    @Headers("Content-Type:application/json")
    @POST("api_entry.php")
    fun getLogoById(@Body body:List<QuaryForNews>) : Observable<List<News>>

    companion object {
        const val ENDPOINT = "https://stankin.ru/"
    }
}