package visapps.mystankin.data.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST
import visapps.mystankin.domain.model.News

interface NewsService {

    @FormUrlEncoded
    @Headers("Content-Type:application/json")
    @POST
    fun getAllNews() : Observable<List<News>>
    @FormUrlEncoded
    @Headers("Content-Type:application/json")
    @POST
    fun getNewsById(@Field("idNews") id:String) : Observable<List<News>>
    companion object {
        const val ENDPOINT = "https://stankin.ru/api_entry.php"
    }
}