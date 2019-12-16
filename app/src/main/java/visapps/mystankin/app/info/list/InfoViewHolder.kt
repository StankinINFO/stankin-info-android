package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.Info
import visapps.mystankin.domain.model.Schedule

class InfoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.info_item, parent, false)) {
    private var mIcon: ImageButton? = null
    private var mTitleView: TextView? = null
    private var mDataView: TextView? = null



    init {
        mDataView = itemView.findViewById(R.id.dataInfo)
        mTitleView = itemView.findViewById(R.id.titleInfo)

    }

    fun bind(info: Info) {
        mDataView?.text = info.data
        mTitleView?.text = info.title

    }

}