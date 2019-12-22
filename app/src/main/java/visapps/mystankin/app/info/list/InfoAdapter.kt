package visapps.mystankin.app.schedule.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.Info
import visapps.mystankin.domain.model.Schedule

class InfoAdapter( private val items :List<Info>) : RecyclerView.Adapter<InfoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.info_item,parent,false)
        return InfoViewHolder( inflater)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val item:Info = items[position]
        holder.bind(item)
    }

}