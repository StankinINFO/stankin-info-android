package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.Schedule

class ScheduleViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.shedule_item, parent, false)) {
    private var mDateView: TextView? = null
    private var mTypeView: TextView? = null
    private var mSubjectView: TextView? = null
    private var mTimeView: TextView? = null
    private var mAuditoriumView: TextView? = null


    init {
        mDateView = itemView.findViewById(R.id.date)
        mTypeView = itemView.findViewById(R.id.type)
        mSubjectView = itemView.findViewById(R.id.subject)
        mTimeView = itemView.findViewById(R.id.time)
        mAuditoriumView = itemView.findViewById(R.id.auditorium)

    }

    fun bind(schedule: Schedule) {
        mDateView?.text = schedule.date
        mTypeView?.text = schedule.type
        mSubjectView?.text = schedule.subject
        mTimeView?.text = schedule.time
        mAuditoriumView?.text = schedule.auditorium

    }

}