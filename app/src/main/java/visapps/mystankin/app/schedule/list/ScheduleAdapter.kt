package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.domain.model.Schedule

class ScheduleAdapter(private val items :List<Schedule>) : RecyclerView.Adapter<ScheduleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ScheduleViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item:Schedule = items[position]
        holder.bind(item)
    }

}