package visapps.mystankin.app.ui.modules

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import visapps.mystankin.app.R

class ModulesFragment : Fragment() {

    companion object {
        fun newInstance() = ModulesFragment()
    }

    private lateinit var viewModel: ModulesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modules_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ModulesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
