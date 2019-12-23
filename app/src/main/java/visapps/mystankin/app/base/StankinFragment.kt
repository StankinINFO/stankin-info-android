package visapps.mystankin.app.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import visapps.mystankin.app.R

open class StankinFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        if(navigationButtonEnabled()){
            toolbar.setNavigationOnClickListener{
                onNavigationButtonPressed()
            }
        }
    }

    open fun onNavigationButtonPressed() {
        findNavController().navigateUp()
    }

    open fun navigationButtonEnabled(): Boolean = false
}