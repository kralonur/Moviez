package com.example.moviez.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviez.databinding.FragmentTvBinding
import com.example.moviez.enums.TVQueryType
import com.example.moviez.model.tv.TV
import com.example.moviez.recview.adapters.TVAdapter
import com.example.moviez.recview.click_listeners.TVClickListener

class TVFragment : Fragment(), TVClickListener {
    private val viewModel by viewModels<TVViewModel>()
    private lateinit var binding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TVAdapter(this)
        binding.viewPager.adapter = adapter

        viewModel.getTvList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.seeAll.setOnClickListener {
            viewModel.navigateCollection {
                navigateCollection(it)
            }
        }

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val queryType = when (checkedId) {
                binding.chipTvTrending.id -> TVQueryType.TRENDING_WEEKLY
                binding.chipTvOnTheAir.id -> TVQueryType.ON_THE_AIR
                binding.chipTvTopRated.id -> TVQueryType.TOP_RATED
                binding.chipTvAiringToday.id -> TVQueryType.AIRING_TODAY
                else -> TVQueryType.POPULAR
            }

            viewModel.changeQueryType(queryType)
        }

    }

    private fun navigateDetail(id: Int) {
        findNavController().navigate(
            TVFragmentDirections.actionTVFragmentToTVDetailFragment(id)
        )
    }

    private fun navigateCollection(queryType: TVQueryType) {
        findNavController().navigate(
            TVFragmentDirections.actionTVFragmentToTVCollectionsTabFragment(queryType)
        )
    }

    override fun onClick(tv_data: TV) {
        navigateDetail(tv_data.id)
    }
}