package com.example.moviez.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviez.databinding.FragmentMovieBinding
import com.example.moviez.enums.MovieQueryType
import com.example.moviez.model.movie.Movie
import com.example.moviez.recview.adapters.MovieAdapter
import com.example.moviez.recview.click_listeners.MovieClickListener

class MovieFragment : Fragment(), MovieClickListener {
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieAdapter(this)
        binding.viewPager.adapter = adapter

        viewModel.getMovieList().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.seeAll.setOnClickListener {
            viewModel.navigateCollection {
                navigateCollection(it)
            }
        }

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val queryType = when (checkedId) {
                binding.chipMovieTrending.id -> MovieQueryType.TRENDING_WEEKLY
                binding.chipMovieNowPlaying.id -> MovieQueryType.NOW_PLAYING
                binding.chipMovieTopRated.id -> MovieQueryType.TOP_RATED
                binding.chipMovieUpcoming.id -> MovieQueryType.UPCOMING
                else -> MovieQueryType.POPULAR
            }

            viewModel.changeQueryType(queryType)
        }

    }

    private fun navigateDetail(id: Int) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(id)
        )
    }

    private fun navigateCollection(queryType: MovieQueryType) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieCollectionsTabFragment(queryType)
        )
    }

    override fun onClick(movie_data: Movie) {
        navigateDetail(movie_data.id)
    }
}