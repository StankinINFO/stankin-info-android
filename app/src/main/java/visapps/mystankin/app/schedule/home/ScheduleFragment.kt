package visapps.mystankin.app.schedule.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.schedule_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.schedule.list.ScheduleAdapter
import visapps.mystankin.app.util.toVisibility
import visapps.mystankin.domain.model.Result
import java.util.*
import javax.inject.Inject

class ScheduleFragment : StankinFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ScheduleViewModel by viewModels {
        viewModelFactory
    }

    private val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        viewModel.changeDate(year, month, dayOfMonth)
    }

    companion object {
        fun newInstance() = ScheduleFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scheduleAdapter = ScheduleAdapter()
        schedule.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = scheduleAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }
        toolbar.inflateMenu(R.menu.schedule)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.select_date -> {
                    showDatePickerDialog()
                    true }
                R.id.forward -> {
                    viewModel.forward()
                    true
                }
                R.id.backward -> {
                    viewModel.backward()
                    true
                }
                else -> true
            }
        }
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.selectGroup)
        }
        selectGroup.setOnClickListener {
            findNavController().navigate(R.id.selectGroup)
        }
        reload.setOnClickListener {
            viewModel.reload()
        }
        viewModel.groupSelectedState.observe(viewLifecycleOwner, Observer { selected->
            selectGroup.visibility = toVisibility(!selected)
            toolbar.menu.setGroupVisible(R.id.date_group, selected)
            toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp)
            updateNavigationIconVisibility(selected)
        })
        viewModel.group.observe(viewLifecycleOwner, Observer {
            toolbar.title = it
        })
        viewModel.date.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()) { toolbar.subtitle = null } else { toolbar.subtitle = it }
        })
        viewModel.schedule.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> println(it.data.size)
                is Result.Loading -> println("loading")
                else -> println("error")
            }
            schedule.visibility = toVisibility(it is Result.Success && it.data.isNotEmpty())
            progressBar.visibility = toVisibility(it is Result.Loading)
            emptyState.visibility = toVisibility(it is Result.Success && it.data.isEmpty())
            errorState.visibility = toVisibility(it is Result.Error)
            if(it is Result.Success) { scheduleAdapter.changeItems(it.data) }

        })
    }

    private fun updateNavigationIconVisibility(visible: Boolean) {
        if(visible){
            toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp)
        }
        else {
            toolbar.navigationIcon = null
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(requireContext(),
            R.style.StankinDialog,
            dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

}
