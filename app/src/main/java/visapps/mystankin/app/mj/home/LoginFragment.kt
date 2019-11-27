package visapps.mystankin.app.mj.home

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import visapps.mystankin.app.R
import visapps.mystankin.app.di.Injectable
import javax.inject.Inject


class LoginFragment : Fragment(), Injectable {
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
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        val editTextLogin = view.findViewById<EditText>(R.id.input_login)
        val editTextPassword = view.findViewById<EditText>(R.id.input_password)
        val button = view.findViewById<Button>(R.id.enter)
        button.setOnClickListener{
            val progressDialog = ProgressDialog(
                context,
                R.style.AppTheme_Dark_Dialog
            )
            progressDialog.isIndeterminate = true
            progressDialog.setMessage("Authenticating...")
            progressDialog.show()
           // progressDialog.dismiss() выключать окно
        }

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}