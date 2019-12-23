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
import visapps.mystankin.app.shared.VersionDialog
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
        Info("site","site","Главный сайт Стакина","https://stankin.ru/"),
        Info("site","site","Электронная обозровательная среда","https://edu.stankin.ru/"),
        Info("address","place","Адрес главного здания","127994, ГСП-4, г. Москва, Вадковский пер., д.1"),
        Info("address","place","Адрес филиала Фрезер","Москва, ш. Фрезер, 10"),
        Info("text","time","График работы приёмной комиссии","Пн.-Пт. - 09.00 - 18.00, Сб. - 09.00 - 15.00"),
        Info("text","time","График работы единого деканата","Пн.-Пт. - 09.00 - 18.00 в 218"),
        Info("phone","phone","Телефон приемной комиссии"," +7 (499) 973-38-49"),
        Info("phone","phone","Телефон единого деканата","+ 7 (499) 973-39-08"),
        Info("email","email","Email приемной комиссии","abitur@stankin.ru"),
        Info("email","email","Email единого деканата","dekanat@stankin.ru")

    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.inflateMenu(R.menu.info)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.info -> {
                    VersionDialog.show(childFragmentManager)
                    true }
                else -> true
            }
        }
        recyclerInfo.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = InfoAdapter(test)
        }
    }

}
