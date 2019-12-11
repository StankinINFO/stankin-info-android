package visapps.mystankin.data.news.api

import io.reactivex.Observable
import retrofit2.http.*
import visapps.mystankin.data.news.models.NewsItemEntity

interface NewsService {

    @POST("api_entry.php")
    fun getNews(@Body body: Request<NewsRequest>) : Observable<Response<NewsResponse>>

    @POST("api_entry.php")
    fun getNewsItem(@Body body: Request<NewsItemRequest>) : Observable<Response<NewsItemEntity>>

    companion object {
        const val ENDPOINT = "https://stankin.ru/"
    }
}