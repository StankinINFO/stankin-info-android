package visapps.mystankin.app.schedule.list

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import visapps.mystankin.app.MainActivity
import visapps.mystankin.app.R
import visapps.mystankin.app.info.home.InfoFragment
import visapps.mystankin.domain.model.Info


class InfoViewHolder(itemview: View) :
    RecyclerView.ViewHolder(itemview) {
    private var mIcon: ImageButton? = null
    private var mTitleView: TextView? = null
    private var mDataView: TextView? = null
    private var test1: Info? = null


    init {
        mDataView = itemView.findViewById(R.id.dataInfo)
        mTitleView = itemView.findViewById(R.id.titleInfo)
        mIcon = itemView.findViewById(R.id.infoImage)
//        itemView.setOnClickListener {
//            test1?.let {
//                test(it)
//            }
//        }

    }

    fun bind(info: Info) {
        mDataView?.text = info.data
        mTitleView?.text = info.title
        val icon = when (info.icon) {
            "phone" -> R.drawable.ic_phone_black_24dp
            "email" -> R.drawable.ic_email_black_24dp
            "place" -> R.drawable.ic_place_black_24dp
            "time" -> R.drawable.ic_access_time_black_24dp
            "school" -> R.drawable.ic_school_black_24dp
            "site" -> R.drawable.ic_language_black_24dp
            else -> R.drawable.ic_school_black_24dp
        }
        mIcon?.setImageResource(icon)
        itemView.setOnClickListener {
            if (icon == R.drawable.ic_phone_black_24dp) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + info.data))
                itemView.context.startActivity(intent)
            } else if (icon == R.drawable.ic_email_black_24dp) {
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + info.data))
                itemView.context.startActivity(Intent.createChooser(intent, ""))
            } else if (icon == R.drawable.ic_place_black_24dp) {
                val uri = Uri.parse("google.navigation:q=" + info.data)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                itemView.context.startActivity(intent)
            } else if (icon == R.drawable.ic_language_black_24dp) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(info.data)
                itemView.context.startActivity(intent)
            }
        }
    }

}

