package visapps.mystankin.app.base

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import visapps.mystankin.app.R

open class StankinFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = view.findViewById<TextView>(R.id.toolbar_title)
        title.setText(titleResId())
        if(navigationButtonEnabled()){
            val backButton = view.findViewById<ImageButton>(R.id.navigation_button)
            backButton.visibility = View.VISIBLE
            backButton.setOnClickListener { onNavigationButtonPressed() }
        }
        if(actionButtonEnabled()) {
            val actionButton = view.findViewById<ImageButton>(R.id.action_button)
            actionButton.visibility = View.VISIBLE
            actionButton.setOnClickListener { onActionButtonPressed() }
        }
    }

    open fun onNavigationButtonPressed() {
        findNavController().navigateUp()
    }

    open fun onActionButtonPressed() {

    }

    open fun titleResId(): Int = R.string.app_name

    open fun navigationButtonEnabled(): Boolean = false

    open fun actionButtonEnabled(): Boolean = false
}