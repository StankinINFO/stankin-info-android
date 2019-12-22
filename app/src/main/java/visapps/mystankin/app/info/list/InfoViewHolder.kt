package visapps.mystankin.app.schedule.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.R
import visapps.mystankin.domain.model.Info

class InfoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.info_item, parent, false)) {
    private var mIcon: ImageButton? = null
    private var mTitleView: TextView? = null
    private var mDataView: TextView? = null



    init {
        mDataView = itemView.findViewById(R.id.dataInfo)
        mTitleView = itemView.findViewById(R.id.titleInfo)
        mIcon = itemView.findViewById(R.id.infoImage)
    }

    fun bind(info: Info) {
        mDataView?.text = info.data
        mTitleView?.text = info.title
        val icon = when(info.icon){
            "phone" -> R.drawable.ic_phone_black_24dp
            "email" -> R.drawable.ic_email_black_24dp
            "place" -> R.drawable.ic_place_black_24dp
            "time" -> R.drawable.ic_access_time_black_24dp
            "school" -> R.drawable.ic_school_black_24dp
            else -> R.drawable.ic_school_black_24dp
        }
        mIcon?.setImageResource(icon)
    }

}