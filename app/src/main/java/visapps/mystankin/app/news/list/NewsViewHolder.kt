package visapps.mystankin.app.news.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.app.util.GlideRequests
import visapps.mystankin.domain.model.News

class NewsViewHolder(private val view: View, private val glide: GlideRequests) :
    RecyclerView.ViewHolder(view) {

    private val title = view.findViewById<TextView>(R.id.title)
    private val desciption = view.findViewById<TextView>(R.id.description)

    private var news: News? = null

    init {
        view.setOnClickListener {

        }
    }

    fun bind(news: News?) {
        this.news = news
        news?.let {
            title.text = it.title
            desciption.text = it.description
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