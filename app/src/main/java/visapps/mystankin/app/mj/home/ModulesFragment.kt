package visapps.mystankin.app.mj.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.modules_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.shared.StankinAlertDialog
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

    override fun titleResId(): Int = R.string.modules

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modules_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        changeSemester.setOnClickListener { StankinAlertDialog.show(childFragmentManager, R.string.wrong_credentials) }
        viewModel.changeSemester(Semester("2019-осень"))
        viewModel.changeUser(User("", "", "", ""))
        viewModel.marks.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Result.Success -> println(it.data[0].subject)
                is Result.Loading -> println("loading")
                else -> println("error")
            }
        })
    }

}
