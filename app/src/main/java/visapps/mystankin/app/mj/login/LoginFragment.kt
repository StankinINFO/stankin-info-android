package visapps.mystankin.app.mj.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.login_fragment.*
import visapps.mystankin.app.R
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.mj.home.ModulesFragment
import visapps.mystankin.app.shared.StankinAlertDialog
import visapps.mystankin.app.util.toVisibility
import visapps.mystankin.domain.model.AuthState
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }
    companion object {
        fun newInstance() = ModulesFragment()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        enter.setOnClickListener {
            viewModel.login(login.text.toString(), password.text.toString())
        }
        viewModel.authState.observe(viewLifecycleOwner, Observer {
            if(it is AuthState.Authenticated) {
                findNavController().navigateUp()
            }
        })
        viewModel.loginState.observe(viewLifecycleOwner, Observer {
            input_login.visibility = toVisibility(it == LoginViewModel.LoginState.NOT_AUTHENTICATED)
            input_password.visibility = toVisibility(it == LoginViewModel.LoginState.NOT_AUTHENTICATED)
            enter.visibility = toVisibility(it == LoginViewModel.LoginState.NOT_AUTHENTICATED)
            progressBar.visibility = toVisibility(it == LoginViewModel.LoginState.LOADING)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            StankinAlertDialog.show(childFragmentManager, R.string.wrong_credentials)
        })
    }
}