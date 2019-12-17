package visapps.mystankin.app.mj.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.SubjectWithMarks

class SubjectViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.mj_item, parent, false)) {
    private var subject: TextView? = null
    private var moduleFirst: TextView? = null
    private var moduleSecond: TextView? = null
    private var course: TextView? = null
    private var offset: TextView? = null
    private var exam: TextView? = null
    private var coef: TextView? = null



    init {
        subject = itemView.findViewById(R.id.textViewSubject)
        moduleFirst = itemView.findViewById(R.id.editTextModuleFirst)
        moduleSecond = itemView.findViewById(R.id.editTextModuleSecond)
        course = itemView.findViewById(R.id.editTextCourse)
        offset = itemView.findViewById(R.id.editTextOffset)
        exam = itemView.findViewById(R.id.editTextExam)
        coef = itemView.findViewById(R.id.editTextCoef)
    }

    fun bind(subjectWithMarks: SubjectWithMarks) {
        subject?.text = subjectWithMarks.subject
        moduleFirst?.text = subjectWithMarks.moduleFirst?.toString() ?: "x"
        moduleSecond?.text = subjectWithMarks.moduleSecond?.toString() ?: "x"
        course?.text = subjectWithMarks.course?.toString() ?: "x"
        offset?.text = subjectWithMarks.offset?.toString() ?: "x"
        exam?.text = subjectWithMarks.offset?.toString() ?: "x"
        coef?.text = subjectWithMarks.coefficient?.toString() ?: "x"
    }

}