package visapps.mystankin.data.news.api

import io.reactivex.Observable
import retrofit2.http.*
import visapps.mystankin.data.news.models.NewsItem

interface NewsService {

    @POST("api_entry.php")
    fun getNews(@Body body: Request<NewsRequest>) : Observable<Response<NewsResponse>>

    @POST("api_entry.php")
    fun getNewsItem(@Body body: Request<NewsItemRequest>) : Observable<Response<NewsItem>>

    companion object {
        const val ENDPOINT = "https://stankin.ru/"
    }
}