package visapps.mystankin.app.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.NewsItem
import visapps.mystankin.domain.model.Result

class NewsItemViewHolder (inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.more_news_item, parent, false)) {
    private var mTitle: TextView? = null



    init {
        mTitle = itemView.findViewById(R.id.titleMoreNews)

    }

    fun bind(newsItem: NewsItem) {
        mTitle?.text=newsItem.title
    }
}