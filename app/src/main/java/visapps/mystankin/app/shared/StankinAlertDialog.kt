package visapps.mystankin.app.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.alert_dialog.*
import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinDialogFragment

class StankinAlertDialog: StankinDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.alert_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ok_button.setOnClickListener { dismiss() }
        val messageTextRes = arguments?.getInt(MESSAGE_TEXT_RES)
        messageTextRes?.let {
            message.text = getString(it)
        }
    }

    companion object {

        private const val MESSAGE_TEXT_RES = "MESSAGE_TEXT_RES "

        fun show(fragmentManager: FragmentManager, messageTextRes: Int) {
            val bundle = Bundle().apply { putInt(MESSAGE_TEXT_RES, messageTextRes) }
            StankinAlertDialog().apply { arguments = bundle }.show(fragmentManager, "AlertDialog")
        }
    }

}