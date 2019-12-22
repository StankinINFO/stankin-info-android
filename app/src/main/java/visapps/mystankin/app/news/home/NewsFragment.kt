package visapps.mystankin.app.news.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.news_fragment.*
import visapps.mystankin.app.R
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.news.list.NewsAdapter
import visapps.mystankin.app.util.GlideApp
import javax.inject.Inject

class NewsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsViewModel by viewModels {
        viewModelFactory
    }
    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        val glide = GlideApp.with(this)
        val subdivision = arguments!!.getInt("subdivision")
        val isMain = arguments!!.getBoolean("is_main")
        val adapter = NewsAdapter(isMain,glide) {

        }
        news_list.adapter = adapter
       viewModel.setup(isMain,subdivision)
        viewModel.newsList?.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer{
            adapter.setNetworkState(it)
        })
    }

}