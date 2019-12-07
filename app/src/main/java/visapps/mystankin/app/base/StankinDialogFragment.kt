package visapps.mystankin.app.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import visapps.mystankin.app.util.toPx

open class StankinDialogFragment: DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val displayMetrics = requireContext().resources.displayMetrics
        val width = displayMetrics.widthPixels - 48.toPx(requireContext())
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}