package visapps.mystankin.app.schedule.selectGroup

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.StudentGroup

class StudentGroupViewHolder(inflater: LayoutInflater,
                             private val parent: ViewGroup,
                             private val onGroupSelected: (StudentGroup) -> Unit) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.student_group_item, parent, false)) {

    private val name: TextView = itemView.findViewById(R.id.name)
    private val speciality: TextView = itemView.findViewById(R.id.speciality)

    private var group: StudentGroup? = null


    init {
        itemView.setOnClickListener {
            group?.let {
                onGroupSelected(it)
            }
            parent.context
        }
    }

    fun bind(group: StudentGroup) {
        this.group = group
        name.text = group.name
        speciality.text = group.speciality
    }

}