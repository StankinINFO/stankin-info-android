package visapps.mystankin.app.mj.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.modules_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.mj.list.ModulesAdapter
import visapps.mystankin.app.schedule.list.InfoAdapter
import visapps.mystankin.app.shared.StankinAlertDialog
import visapps.mystankin.app.util.toVisibility
import visapps.mystankin.domain.model.*
import javax.inject.Inject

class ModulesFragment : StankinFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ModulesViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance() = ModulesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modules_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.inflateMenu(R.menu.modules)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.exit -> {
                    viewModel.exit()
                    true }
                else -> true
            }
        }
        reload.setOnClickListener {
            viewModel.refresh()
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                viewModel.changeSemester(parent.adapter.getItem(position).toString())
            }
        }
        val modulesAdapter = ModulesAdapter()
        modulesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = modulesAdapter
        }
        viewModel.semesters.observe(viewLifecycleOwner, Observer {
            modules.visibility = toVisibility(it is Result.Success)
            if(it is Result.Success) {
                spinner.adapter = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_spinner_dropdown_item, it.data)
            }
            errorState.visibility = toVisibility(it is Result.Error)
            swipeRefresh.isRefreshing = it is Result.Loading
            swipeRefresh.isEnabled = it !is Result.Error
        })
        viewModel.marks.observe(viewLifecycleOwner, Observer {
            modules.visibility = toVisibility(it is Result.Success)
            errorState.visibility = toVisibility(it is Result.Error)
            swipeRefresh.isRefreshing = it is Result.Loading
            swipeRefresh.isEnabled = it !is Result.Error
            if(it is Result.Success) {
                modulesAdapter.updateMarks(it.data)
            }
        })
        viewModel.authState.observe(viewLifecycleOwner, Observer {
            if(it is AuthState.NotAuthenticated){
                findNavController().navigate(R.id.loginFragment)
            }
        })
    }

}
