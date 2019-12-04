package visapps.mystankin.app.news.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

import visapps.mystankin.app.R
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.domain.model.DataForQuary
import visapps.mystankin.domain.model.QuaryForNews
import javax.inject.Inject

class NewsHostFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsHostViewModel by viewModels {
        viewModelFactory
    }
    companion object {
        fun newInstance() = NewsHostFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_host_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val a= QuaryForNews("getNews", DataForQuary(true,false,0,6,1,"",""))
        viewModel.loadSemesters(a)
    }

}
