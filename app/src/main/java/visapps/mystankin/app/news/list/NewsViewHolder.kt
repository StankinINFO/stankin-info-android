package visapps.mystankin.app.news.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.app.util.GlideRequests
import visapps.mystankin.data.news.api.NewsService
import visapps.mystankin.domain.model.News
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(private val view: View, private val glide: GlideRequests) :
    RecyclerView.ViewHolder(view) {

    private val title = view.findViewById<TextView>(R.id.title)
    private val date = view.findViewById<TextView>(R.id.date)
    private val image = view.findViewById<ImageView>(R.id.imageView)

    //val str:String

    private var news: News? = null

    init {
        view.setOnClickListener {

        }
    }

    fun bind(news: News?) {
        this.news = news
        news?.let {

            title.text = it.title
            val timeStamp = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(it.date)
            date.text = timeStamp
            glide.load(NewsService.ENDPOINT+it.logo).into(image)

        }
    }

    companion object {
        fun create(parent: ViewGroup, glide: GlideRequests): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
            return NewsViewHolder(view, glide)
        }
    }

}