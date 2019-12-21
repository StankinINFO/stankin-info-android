package visapps.mystankin.app.schedule.selectGroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import kotlinx.android.synthetic.main.select_group_fragment.*
import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.util.toVisibility
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
        val groupsAdapter = StudentGroupAdapter {
            viewModel.selectGroup(it, 0)
            findNavController().navigateUp()
        }
        groupsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = groupsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }
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
        reload.setOnClickListener {
            viewModel.reload()
        }
        viewModel.groups.observe(viewLifecycleOwner, Observer{
            groupsList.visibility = toVisibility(it is Result.Success && it.data.isNotEmpty())
            progressBar.visibility = toVisibility(it is Result.Loading)
            emptyState.visibility = toVisibility(it is Result.Success && it.data.isEmpty())
            errorState.visibility = toVisibility(it is Result.Error)
            if(it is Result.Success) { groupsAdapter.changeGroups(it.data) }
        })
    }

}
