package visapps.mystankin.app.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.app.util.GlideRequests
import visapps.mystankin.data.news.api.NewsService
import visapps.mystankin.domain.model.News
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(private val a:Boolean,private val view: View, private val glide: GlideRequests) :
    RecyclerView.ViewHolder(view) {

    private val title = view.findViewById<TextView>(R.id.title)
    private val date = view.findViewById<TextView>(R.id.date)
    private val image = view.findViewById<ImageView>(R.id.imageView)


    private var news: News? = null

    init {
        view.setOnClickListener {
            val bundle = Bundle()
            news?.let { bundle.putInt("id",it.id )}
            findNavController(view).navigate(R.id.moreNewsFragment,bundle)

        }
    }
    fun bind(news: News?) {
        this.news = news
        news?.let {
            title.text = it.title
            val timeStamp = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(it.date)
            date.text = timeStamp
            if (a!=false) {
                glide.load(NewsService.ENDPOINT + it.logo).into(image)
            }

        }
    }

    companion object {
        fun create(a:Boolean,parent: ViewGroup, glide: GlideRequests): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
            return NewsViewHolder(a,view, glide)
        }
    }

}