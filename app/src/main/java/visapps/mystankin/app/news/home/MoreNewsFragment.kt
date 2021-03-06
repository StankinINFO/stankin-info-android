package visapps.mystankin.app.news.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.more_news_fragment.*
import visapps.mystankin.app.R
import visapps.mystankin.app.di.Injectable
import javax.inject.Inject

class MoreNewsFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: NewsItemViewModel by viewModels {
        viewModelFactory
    }

    companion object {
        fun newInstance() = NewsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.more_news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        arguments?.getInt("id")?.let {
            viewModel.setup(it)
        }
        viewModel.title.observe(viewLifecycleOwner, Observer {
            toolbar.title = it ?: ""
        })
        viewModel.news.observe(viewLifecycleOwner, Observer {
            //   val adapter = NewsItemAdapter(it)
            // newsItem.adapter=adapter
        })
        //initAdapter()
    }

//private fun initAdapter() {
//    val glide = GlideApp.with(this)
//    val subdivision = arguments!!.getInt("subdivision")
//    val isMain = arguments!!.getBoolean("is_main")
//    val adapter = NewsAdapter(isMain,glide) {
//
//    }
//    news_list.adapter = adapter
//
//    println(isMain)
//    viewModel.setup(isMain,subdivision)
//    viewModel.newsList?.observe(viewLifecycleOwner, Observer {
//        adapter.submitList(it)
//    })
//    viewModel.networkState.observe(viewLifecycleOwner, Observer{
//        adapter.setNetworkState(it)
//    })
//}

}