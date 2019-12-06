package visapps.mystankin.app.news.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.news_host_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.news.list.NewsAdapter
import visapps.mystankin.app.util.GlideApp
import visapps.mystankin.domain.model.NewsQuery
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
        initAdapter()
    }

    private fun initAdapter() {
        val glide = GlideApp.with(this)
        val adapter = NewsAdapter(glide) {

        }
        news_list.adapter = adapter
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.networkState.observe(viewLifecycleOwner, Observer{
            adapter.setNetworkState(it)
        })
    }

}
