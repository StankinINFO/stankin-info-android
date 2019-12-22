package visapps.mystankin.app.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.schedule.list.InfoViewHolder
import visapps.mystankin.domain.model.NewsItem
import visapps.mystankin.domain.model.Result

class NewsItemAdapter(private val item: NewsItem) : RecyclerView.Adapter<NewsItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsItemViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item:NewsItem = item
        holder.bind(item)
    }
}