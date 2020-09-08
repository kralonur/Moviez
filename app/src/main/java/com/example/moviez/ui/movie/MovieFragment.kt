package com.example.moviez.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        viewModel.movieList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.seeAll.setOnClickListener {
            //TODO("Not yet implemented")
        }

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.chipMovieTrending.id -> viewModel.changeQueryType(MovieQueryType.TRENDING_WEEKLY)
                binding.chipMoviePopular.id -> viewModel.changeQueryType(MovieQueryType.POPULAR)
                binding.chipMovieNowPlaying.id -> viewModel.changeQueryType(MovieQueryType.NOW_PLAYING)
                binding.chipMovieTopRated.id -> viewModel.changeQueryType(MovieQueryType.TOP_RATED)
                binding.chipMovieUpcoming.id -> viewModel.changeQueryType(MovieQueryType.UPCOMING)
            }
        }
    }

    override fun onClick(movie_data: Movie) {
        //TODO("Not yet implemented")
    }
}