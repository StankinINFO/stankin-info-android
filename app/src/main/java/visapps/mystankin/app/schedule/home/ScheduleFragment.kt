package visapps.mystankin.app.schedule.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.schedule_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println(viewModel.toString())
    }

}
