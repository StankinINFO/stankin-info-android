package visapps.mystankin.app.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.version_dialog.*
import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinDialogFragment

class VersionDialog: StankinDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.version_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ok_button.setOnClickListener { dismiss() }
    }

    companion object {

        fun show(fragmentManager: FragmentManager) {
            VersionDialog().show(fragmentManager, "VersionDialog")
        }
    }

}