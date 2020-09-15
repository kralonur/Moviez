package com.example.moviez.ui.star_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.moviez.databinding.FragmentStarAboutBinding

class StarAboutFragment : Fragment() {
    private val viewModel by activityViewModels<StarDetailViewModel>()
    private lateinit var binding: FragmentStarAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarAboutBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.starDetail.observe(viewLifecycleOwner) {
            binding.personDetails = it
            binding.executePendingBindings()
        }
    }
}