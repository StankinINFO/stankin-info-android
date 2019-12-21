package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.domain.model.ScheduleItem

class ScheduleAdapter() : RecyclerView.Adapter<ScheduleViewHolder>(){

    private val items = mutableListOf<ScheduleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ScheduleViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item:ScheduleItem = items[position]
        holder.bind(item)
    }

    fun changeItems(items: List<ScheduleItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}