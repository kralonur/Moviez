package com.example.moviez.ui.star_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviez.databinding.LayoutRecviewBinding
import com.example.moviez.model.cast.MovieCast
import com.example.moviez.recview.adapters.MovieCastAdapter
import com.example.moviez.recview.click_listeners.MovieCastClickListener

class StarCreditMovieFragment : Fragment(), MovieCastClickListener {
    private val viewModel by activityViewModels<StarDetailViewModel>()
    private lateinit var binding: LayoutRecviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutRecviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieCastAdapter(this)

        binding.recView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.starDetail.observe(viewLifecycleOwner) {
            adapter.submitList(it.movieCredits.cast)
        }

    }

    private fun navigateDetail(id: Int) {
        findNavController().navigate(
            StarDetailFragmentDirections.actionStarDetailFragmentToMovieDetailFragment(id)
        )
    }

    override fun onClick(castData: MovieCast) {
        navigateDetail(castData.id)
    }
}