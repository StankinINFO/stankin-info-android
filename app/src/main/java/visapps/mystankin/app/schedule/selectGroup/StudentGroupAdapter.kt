package visapps.mystankin.app.schedule.selectGroup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.domain.model.StudentGroup

class StudentGroupAdapter(private val onGroupSelected: (StudentGroup) -> Unit) : RecyclerView.Adapter<StudentGroupViewHolder>() {

    private val groups = mutableListOf<StudentGroup>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentGroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StudentGroupViewHolder(inflater,parent, onGroupSelected)
    }

    override fun getItemCount(): Int = groups.size

    override fun onBindViewHolder(holder: StudentGroupViewHolder, position: Int) = holder.bind(groups[position])

    fun changeGroups(groups: List<StudentGroup>) {
        this.groups.clear()
        this.groups.addAll(groups)
        notifyDataSetChanged()
    }

}