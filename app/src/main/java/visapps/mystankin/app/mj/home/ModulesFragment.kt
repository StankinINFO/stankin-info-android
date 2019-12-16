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
import kotlinx.android.synthetic.main.modules_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.shared.StankinAlertDialog
import visapps.mystankin.domain.model.AuthState
import visapps.mystankin.domain.model.Result
import visapps.mystankin.domain.model.Semester
import visapps.mystankin.domain.model.User
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modules_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var op = arrayOf("2017-весна","2017-осень","2018-весна")
        spinner.adapter = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_spinner_dropdown_item,op)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }
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
                findNavController().navigate(R.id.loginFragment)
            }
        })
    }

}
