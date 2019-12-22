package visapps.mystankin.app.mj.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.domain.model.SubjectWithMarks

class ModulesAdapter() : RecyclerView.Adapter<SubjectViewHolder>(){

    private val marks = mutableListOf<SubjectWithMarks>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubjectViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = marks.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(marks[position])
    }

    fun updateMarks(marks: List<SubjectWithMarks>) {
        this.marks.clear()
        this.marks.addAll(marks)
        notifyDataSetChanged()
    }

}