package visapps.mystankin.app.info.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
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
//
//        try {
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("+79252765617"))
//            activity!!.startActivity(intent)
//        } catch (e: Exception) {
//            e.printStackTrace()
//
//        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
