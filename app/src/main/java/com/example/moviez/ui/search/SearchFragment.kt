package com.example.moviez.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.moviez.R
import com.example.moviez.databinding.FragmentSearchBinding
import com.example.moviez.recview.adapters.FragmentsPagerAdapter
import com.example.moviez.ui.search.movie.SearchMovieFragment
import com.example.moviez.ui.search.star.SearchStarFragment
import com.example.moviez.ui.search.tv.SearchTVFragment
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class SearchFragment : Fragment() {
    private val viewModel by activityViewModels<SearchViewModel>()
    private lateinit var binding: FragmentSearchBinding

    private val titles by lazy {
        listOf(
            getString(R.string.movies),
            getString(R.string.tv_shows),
            getString(R.string.stars)
        )
    }

    private val fragments =
        listOf(
            SearchMovieFragment(),
            SearchTVFragment(),
            SearchStarFragment()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        Timber.i(query)
                        viewModel.updateQuery(query)
                        return true
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            }
        )

        val adapter = FragmentsPagerAdapter(
            fragments,
            childFragmentManager,
            lifecycle
        )

        binding.layoutCollectionTab.viewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = fragments.size
            setCurrentItem(0, true)
        }

        TabLayoutMediator(
            binding.layoutCollectionTab.tabLayout,
            binding.layoutCollectionTab.viewPager
        ) { tab, position ->
            tab.text = titles.getOrNull(position)
        }.attach()
    }

}