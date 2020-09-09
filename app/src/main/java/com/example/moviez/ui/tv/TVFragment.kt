package com.example.moviez.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        viewModel.tvList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.seeAll.setOnClickListener {
            //TODO("Not yet implemented")
        }

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.chipTvTrending.id -> viewModel.changeQueryType(TVQueryType.TRENDING_WEEKLY)
                binding.chipTvPopular.id -> viewModel.changeQueryType(TVQueryType.POPULAR)
                binding.chipTvOnTheAir.id -> viewModel.changeQueryType(TVQueryType.ON_THE_AIR)
                binding.chipTvTopRated.id -> viewModel.changeQueryType(TVQueryType.TOP_RATED)
                binding.chipTvAiringToday.id -> viewModel.changeQueryType(TVQueryType.AIRING_TODAY)
            }
        }
    }

    override fun onClick(tv_data: TV) {
        //TODO("Not yet implemented")
    }
}