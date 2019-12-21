package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.domain.model.Info

class InfoAdapter(private val items :List<Info>) : RecyclerView.Adapter<InfoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return InfoViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val item:Info = items[position]
        holder.bind(item)
    }

}