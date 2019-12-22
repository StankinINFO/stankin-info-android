package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.ScheduleItem

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

    fun bind(scheduleItem: ScheduleItem) {
        mStartView?.text = scheduleItem.time.substringBefore('-').replace(" ", "", true)
        mEndView?.text = scheduleItem.time.substringAfter('-').replace(" ", "", true)
        mTypeView?.text = scheduleItem.studyType
        mSubjectView?.text = scheduleItem.subject
        mAuditoriumView?.text = scheduleItem.room
        mTeacherView?.text = scheduleItem.teacher
    }

}