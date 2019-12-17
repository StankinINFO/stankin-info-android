package visapps.mystankin.app.mj.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

    private val test = listOf(
        SubjectWithMarks("Архитектура цифрового производства и предприятия", 54, 0, null, null, 0, 4.0),
        SubjectWithMarks("Интернет-технологии", 52, 0, null, null, 0, 4.0),
        SubjectWithMarks("Инфографика", 45, 45, null, 0, null, 3.5),
        SubjectWithMarks("Математическое и компьютерное моделирование", 45, 45, null, null, 0, 3.5),
        SubjectWithMarks("Методология научных исследований", 40, 0, null, 0, 0, 3.0),
        SubjectWithMarks("Технический иностранный язык", 45, 45, null, 45, null, 3.0)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modules_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var op = arrayOf("2019-осень","2017-осень","2018-весна")
        spinner.adapter = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_spinner_dropdown_item,op)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
        }

        modulesList.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(requireContext())
            // set the custom adapter to the RecyclerView
            adapter = ModulesAdapter(test)

            //addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
        //changeSemester.setOnClickListener { viewModel.changeSemester(Semester("2019-осень")) }
        //viewModel.changeSemester(Semester("2019-осень"))
        //viewModel.changeUser(User("", "", "", ""))
        viewModel.marks.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> println(it.data[0].subject)
                is Result.Loading -> println("loading")
                else -> println("error")
            }
        })
        viewModel.authState.observe(viewLifecycleOwner, Observer {
            if(it is AuthState.NotAuthenticated){
               // findNavController().navigate(R.id.loginFragment)
            }
        })
    }

}
