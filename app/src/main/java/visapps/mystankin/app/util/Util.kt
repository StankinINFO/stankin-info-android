package visapps.mystankin.app.util

import android.content.Context
import android.view.View

fun toVisibility(constraint : Boolean): Int {
    return if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun Int.toPx(context: Context) : Int = (this * context.resources.displayMetrics.density).toInt()