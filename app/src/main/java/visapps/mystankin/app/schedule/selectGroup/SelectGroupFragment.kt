package visapps.mystankin.app.schedule.selectGroup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.select_group_fragment.*
import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.domain.model.Result
import javax.inject.Inject

class SelectGroupFragment : StankinFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SelectGroupViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_group_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String): Boolean {
                viewModel.search(query)
                return true
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return false
            }
        })
        viewModel.groups.observe(viewLifecycleOwner, Observer{
            when(it) {
                is Result.Success -> println(it.data.size)
                is Result.Loading -> println("loading")
                else -> println("error")
            }
        })
    }


}
