package visapps.mystankin.app.schedule.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.schedule_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.schedule.list.InfoAdapter
import visapps.mystankin.app.schedule.list.ScheduleAdapter
import visapps.mystankin.domain.model.Schedule
import javax.inject.Inject

class ScheduleFragment : StankinFragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ScheduleViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance() = ScheduleFragment()
    }
    private val test = listOf(
        Schedule("13 сентрября","лекция","моделирование","14:00-15:00", "421"),
        Schedule("12 сентрября","семинар","бд","14:00-15:00", "555"),
        Schedule("11 сентрября","лекция","ис","14:00-15:00", "333"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        recyclerSchedule.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = ScheduleAdapter(test)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println(viewModel.toString())
    }

}
