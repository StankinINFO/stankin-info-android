package visapps.mystankin.app.info.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.info_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.schedule.list.InfoAdapter
import visapps.mystankin.domain.model.Info
import visapps.mystankin.domain.model.Schedule
import javax.inject.Inject

class InfoFragment : StankinFragment(),Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
//    private val viewModel: InfoViewModel by viewModels {
//        viewModelFactory
//    }

    companion object {
        fun newInstance() = InfoFragment()
    }
    private val test = listOf(
        Info("адрес","","тест1","тест2"),
        Info("номер","","тест2","тест3")
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        recyclerInfo.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = InfoAdapter(test)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
