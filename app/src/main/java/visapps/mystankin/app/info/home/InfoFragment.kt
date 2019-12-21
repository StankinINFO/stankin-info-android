package visapps.mystankin.app.info.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.info_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.schedule.list.InfoAdapter
import visapps.mystankin.domain.model.Info
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
        Info("text","school","Название университета","ФГБОУ ВО \"МГТУ \"СТАНКИН\""),
        Info("address","place","Юридический адрес","127055, Москва, Вадковский пер., д.3а"),
        Info("text","time","График работы","Пн.-Чт. - 09.00 - 18.00 Пт. - 09.00 - 16.45, сб.-вс. - выходные дни."),
        Info("phone","phone","Телефон приемной ректора","(499) 973-30-66"),
        Info("email","email","Email приемной ректора","rector@stankin.ru")
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
            layoutManager = LinearLayoutManager(requireContext())
            // set the custom adapter to the RecyclerView
            adapter = InfoAdapter(test)

            //addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
