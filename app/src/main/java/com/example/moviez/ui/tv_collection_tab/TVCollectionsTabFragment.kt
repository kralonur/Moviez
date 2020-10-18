package com.example.moviez.ui.tv_collection_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.moviez.R
import com.example.moviez.databinding.LayoutCollectionTabBinding
import com.example.moviez.enums.TVQueryType
import com.example.moviez.recview.adapters.FragmentsPagerAdapter
import com.example.moviez.ui.tv_collection.TVCollectionFragment
import com.example.moviez.util.whenReady
import com.google.android.material.tabs.TabLayoutMediator

class TVCollectionsTabFragment : Fragment() {
    private val args by navArgs<TVCollectionsTabFragmentArgs>()
    private lateinit var binding: LayoutCollectionTabBinding

    private val fragments =
        listOf<Fragment>(
            TVCollectionFragment.newInstance(TVQueryType.POPULAR),
            TVCollectionFragment.newInstance(TVQueryType.TOP_RATED),
            TVCollectionFragment.newInstance(TVQueryType.AIRING_TODAY),
            TVCollectionFragment.newInstance(TVQueryType.ON_THE_AIR),
            TVCollectionFragment.newInstance(TVQueryType.TRENDING_DAILY),
            TVCollectionFragment.newInstance(TVQueryType.TRENDING_WEEKLY)
        )

    private val titles by lazy {
        listOf(
            getString(R.string.popular),
            getString(R.string.top_rated),
            getString(R.string.airing_today),
            getString(R.string.on_the_air),
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

        val tvQueryType = args.queryType

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
            if (!viewPagerSet) setQuery(tvQueryType)
            viewPagerSet = true
        }


    }

    private fun setQuery(query: TVQueryType) {
        with(binding.viewPager) {
            when (query) {
                TVQueryType.POPULAR -> this.setCurrentItem(0, true)
                TVQueryType.TOP_RATED -> this.setCurrentItem(1, true)
                TVQueryType.AIRING_TODAY -> this.setCurrentItem(2, true)
                TVQueryType.ON_THE_AIR -> this.setCurrentItem(3, true)
                TVQueryType.TRENDING_DAILY -> this.setCurrentItem(4, true)
                TVQueryType.TRENDING_WEEKLY -> this.setCurrentItem(5, true)
            }
        }
    }
}