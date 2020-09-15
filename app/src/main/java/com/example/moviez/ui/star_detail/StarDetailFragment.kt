package com.example.moviez.ui.star_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.moviez.R
import com.example.moviez.databinding.FragmentStarDetailBinding
import com.example.moviez.recview.adapters.FragmentsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class StarDetailFragment : Fragment() {
    private val viewModel by activityViewModels<StarDetailViewModel>()
    private val args by navArgs<StarDetailFragmentArgs>()
    private lateinit var binding: FragmentStarDetailBinding


    private val titles by lazy {
        listOf(
            getString(R.string.about),
            getString(R.string.movies),
            getString(R.string.tv_shows)
        )
    }

    private val fragments =
        listOf(
            StarAboutFragment(),
            StarCreditMovieFragment(),
            StarCreditTVFragment()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personId = args.personId

        viewModel.setPerson(personId)

        viewModel.starDetail.observe(viewLifecycleOwner) {
            it?.let {
                binding.personDetails = it
                binding.executePendingBindings()
            }
        }

        val adapter = FragmentsPagerAdapter(
            fragments,
            childFragmentManager,
            lifecycle
        )

        binding.layoutCollectionTab.viewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = fragments.size
        }

        TabLayoutMediator(
            binding.layoutCollectionTab.tabLayout,
            binding.layoutCollectionTab.viewPager
        ) { tab, position ->
            tab.text = titles.getOrNull(position)
        }.attach()

    }

}