package visapps.mystankin.app.news.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.news_fragment.*
import kotlinx.android.synthetic.main.news_host_fragment.*

import visapps.mystankin.app.R
import visapps.mystankin.app.base.StankinFragment
import visapps.mystankin.app.di.Injectable
import visapps.mystankin.app.news.list.NewsAdapter
import visapps.mystankin.app.news.list.ViewPagerAdapter
import visapps.mystankin.app.util.GlideApp
import javax.inject.Inject

class NewsHostFragment : StankinFragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

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
       // initAdapter()
        viewPager2.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when(position) {
                    0 -> {

                        tab.text = "Новости"

                    }
                    1 -> {
                        tab.text = "Новости деканата"

                    }
                }
            }).attach()

    }

//    private fun initAdapter() {
//        val glide = GlideApp.with(this)
//        val adapter = NewsAdapter(glide) {
//
//        }
//        news_list.adapter = adapter
//        viewModel.newsList.observe(viewLifecycleOwner, Observer {
//            adapter.submitList(it)
//        })
//        viewModel.networkState.observe(viewLifecycleOwner, Observer{
//            adapter.setNetworkState(it)
//        })
//    }

}
