package com.example.moviez.ui.movie_collection_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.moviez.R
import com.example.moviez.databinding.LayoutCollectionTabBinding
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.recview.adapters.FragmentsPagerAdapter
import com.example.moviez.ui.movie_collection.MovieCollectionFragment
import com.example.moviez.util.whenReady
import com.google.android.material.tabs.TabLayoutMediator

class MovieCollectionsTabFragment : Fragment() {
    private val args by navArgs<MovieCollectionsTabFragmentArgs>()
    private lateinit var binding: LayoutCollectionTabBinding

    private val fragments =
        listOf<Fragment>(
            MovieCollectionFragment.newInstance(MovieQueryType.POPULAR),
            MovieCollectionFragment.newInstance(MovieQueryType.TOP_RATED),
            MovieCollectionFragment.newInstance(MovieQueryType.UPCOMING),
            MovieCollectionFragment.newInstance(MovieQueryType.NOW_PLAYING),
            MovieCollectionFragment.newInstance(MovieQueryType.TRENDING_DAILY),
            MovieCollectionFragment.newInstance(MovieQueryType.TRENDING_WEEKLY)
        )

    private val titles by lazy {
        listOf(
            getString(R.string.popular),
            getString(R.string.top_rated),
            getString(R.string.upcoming),
            getString(R.string.now_playing),
            getString(R.string.trending_daily),
            getString(R.string.trending_weekly)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutCollectionTabBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieQueryType = args.queryType

        val adapter = FragmentsPagerAdapter(
            fragments,
            childFragmentManager,
            lifecycle
        )

        binding.viewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = fragments.size
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles.getOrNull(position)
        }.attach()

        var viewPagerSet = false
        binding.viewPager.whenReady {
            if (!viewPagerSet) setQuery(movieQueryType)
            viewPagerSet = true
        }

    }

    private fun setQuery(query: MovieQueryType) {
        with(binding.viewPager) {
            when (query) {
                MovieQueryType.POPULAR -> this.setCurrentItem(0, true)
                MovieQueryType.TOP_RATED -> this.setCurrentItem(1, true)
                MovieQueryType.UPCOMING -> this.setCurrentItem(2, true)
                MovieQueryType.NOW_PLAYING -> this.setCurrentItem(3, true)
                MovieQueryType.TRENDING_DAILY -> this.setCurrentItem(4, true)
                MovieQueryType.TRENDING_WEEKLY -> this.setCurrentItem(5, true)
            }
        }
    }
}