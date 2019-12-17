package visapps.mystankin.app.mj.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.domain.model.SubjectWithMarks

class ModulesAdapter(private val items :List<SubjectWithMarks>) : RecyclerView.Adapter<SubjectViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubjectViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(items[position])
    }

}