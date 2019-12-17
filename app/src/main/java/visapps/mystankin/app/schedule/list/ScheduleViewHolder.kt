package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.Schedule

class ScheduleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.shedule_item, parent, false)) {
    private var mStartView: TextView? = null
    private var mEndView: TextView? = null
    private var mTypeView: TextView? = null
    private var mSubjectView: TextView? = null
    private var mAuditoriumView: TextView? = null
    private var mTeacherView: TextView? = null


    init {
        mStartView = itemView.findViewById(R.id.start)
        mEndView = itemView.findViewById(R.id.end)
        mTypeView = itemView.findViewById(R.id.type)
        mSubjectView = itemView.findViewById(R.id.subject)
        mAuditoriumView = itemView.findViewById(R.id.auditorium)
        mTeacherView = itemView.findViewById(R.id.teacher)
    }

    fun bind(schedule: Schedule) {
        mStartView?.text = schedule.time.substringBefore('-').replace(" ", "", true)
        mEndView?.text = schedule.time.substringAfter('-').replace(" ", "", true)
        mTypeView?.text = schedule.type
        mSubjectView?.text = schedule.subject
        mAuditoriumView?.text = schedule.auditorium
        mTeacherView?.text = schedule.teacher
    }

}