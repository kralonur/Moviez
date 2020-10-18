package com.example.moviez.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviez.databinding.FragmentMovieDetailBinding
import com.example.moviez.model.cast.Cast
import com.example.moviez.recview.adapters.CastAdapter
import com.example.moviez.recview.click_listeners.CastClickListener

class MovieDetailFragment : Fragment(), CastClickListener {
    private val viewModel by viewModels<MovieDetailViewModel>()
    private lateinit var binding: FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = args.movieId

        viewModel.setMovie(movieId)

        val adapter = CastAdapter(this)
        binding.recView.adapter = adapter

        viewModel.movieDetail.observe(viewLifecycleOwner) {
            it?.let {
                binding.movie = it
                adapter.submitList(it.credits.casts)
                binding.executePendingBindings()
            }
        }

    }

    private fun navigateStar(id:Int){
        findNavController().navigate(
            MovieDetailFragmentDirections.actionMovieDetailFragmentToStarDetailFragment(id)
        )
    }

    override fun onClick(cast_data: Cast) {
        navigateStar(cast_data.id)
    }
}