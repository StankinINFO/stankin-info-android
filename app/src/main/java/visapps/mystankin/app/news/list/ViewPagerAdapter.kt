package visapps.mystankin.app.news.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import visapps.mystankin.app.news.home.NewsHostFragment
import visapps.mystankin.app.news.home.NewsFragment

class ViewPagerAdapter(fm: NewsHostFragment): FragmentStateAdapter(fm) {
    val eventList = listOf("0", "1")

    override fun getItemCount(): Int {
        return eventList.count()
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return NewsFragment().apply {arguments = Bundle().apply {
                    putBoolean("is_main",true)
                    putInt("subdivision",0)
                    }
                }

            }
            else ->
                return NewsFragment().apply {arguments = Bundle().apply {
                    putBoolean("is_main",false)
                    putInt("subdivision",125)
                }
                }
        }
        }
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        EventViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_fragment, parent, false))
//
//    override fun getItemCount() = eventList.count()
//    override fun createFragment(position: Int): Fragment {
//
//    }
//
//    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
//
//    }
//
//    class EventViewHolder(val view: View) : RecyclerView.ViewHolder(view)

