package com.example.moviez.ui.tv_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviez.databinding.FragmentTvDetailBinding
import com.example.moviez.model.cast.Cast
import com.example.moviez.recview.adapters.CastAdapter
import com.example.moviez.recview.click_listeners.CastClickListener

class TVDetailFragment : Fragment(), CastClickListener {
    private val viewModel by viewModels<TVDetailViewModel>()
    private lateinit var binding: FragmentTvDetailBinding
    private val args by navArgs<TVDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvId = args.tvId

        viewModel.setTv(tvId)

        val adapter = CastAdapter(this)

        binding.recView.adapter = adapter

        viewModel.tvDetail.observe(viewLifecycleOwner) {
            it?.let {
                binding.tv = it
                adapter.submitList(it.credits.casts)
                binding.executePendingBindings()
            }
        }

    }

    private fun navigateStar(id: Int) {
        findNavController().navigate(
            TVDetailFragmentDirections.actionTVDetailFragmentToStarDetailFragment(id)
        )
    }

    override fun onClick(cast_data: Cast) {
        navigateStar(cast_data.id)
    }
}