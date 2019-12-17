package visapps.mystankin.app.schedule.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.news_item.*
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
        Schedule("Лекция","Интернет-технологии","16:00 - 17:40", "0206", "Овчинников П.Е."),
        Schedule("Лекция","Инфографика","18:00 - 19:30", "0209", "Локтев М.А."),
        Schedule("Семинар","Математическое и компьютерное моделирование","19:40 - 21:10", "357(з)", "Бабарин С.С."))
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

            addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        }
        toolbar.title = "ИДМ-19-04"
        toolbar.subtitle = "12-09-2019"
        toolbar.inflateMenu(R.menu.schedule)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println(viewModel.toString())
    }

}
