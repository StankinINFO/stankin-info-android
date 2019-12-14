package visapps.mystankin.app.news.list

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.app.util.GlideRequests
import visapps.mystankin.domain.model.NetworkState
import visapps.mystankin.domain.model.News

class NewsAdapter(private val a:Boolean,private val glide: GlideRequests, private val reloadCallback: () -> Unit)
    : PagedListAdapter<News, RecyclerView.ViewHolder>(NEWS_COMPARATOR
){

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.news_item-> (holder as NewsViewHolder).bind(getItem(position))
            R.layout.network_state_item -> (holder as NetworkStateViewHolder).bindTo(
                networkState)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.news_item -> NewsViewHolder.create(
                a,
                parent,
                glide
            )
            R.layout.network_state_item -> NetworkStateViewHolder.create(
                parent,
                reloadCallback
            )
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.SUCCESS

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.news_item
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
                oldItem.id == newItem.id

            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }
}